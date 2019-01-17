package org.hj.timefilter.anno;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hj.timefilter.exception.MyException;
import org.hj.timefilter.util.IPUtil;
import org.hj.timefilter.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class RequestLimitContract {

    @Autowired
    private RedisUtil redisUtil;
    // 作用位置为所有的controller 类型为注解类型  RequestLimit limit
    // 访问控制前缀
    private String qz = "RL";
    @Before("within(@org.springframework.stereotype.Controller *) && @annotation(limit)")
    public void requestlimt(JoinPoint joinpoint , RequestLimit limit) throws MyException {
        // 获取切入点方法的所有参数
        Object[] args = joinpoint.getArgs();
        HttpServletRequest request = null;
        for (Object o: args) {
            if(o instanceof HttpServletRequest){
                request = (HttpServletRequest) o;
                break;
            }
        }
        String url = request.getRequestURI();
        if (request == null) {

            throw new MyException("1001","缺失参数",url,"方法中缺失HttpServletRequest参数");
        }
        // 获取ip
        String ipAddr = IPUtil.getIpAddr(request);
        //System.out.println(ipAddr+"******************");
        // 查看redis 中是否该ip
        String key = qz+ipAddr;
        boolean b = redisUtil.hasKey(key);
        if(b){
            // 存在则判断访问次数
            int num = Integer.parseInt(redisUtil.get(key).toString());
            if(limit.count()==num){
                // 访问次数达到上限  不能访问
                throw new MyException("1001","操作太频繁，请稍后再试！",url,limit.time()+"秒钟只能访问"+limit.count()+"次");
            }else{
                //访问次数加一
                num++;
                // 获取过期时间
                redisUtil.set(key,num,redisUtil.getExpire(key));
            }
        }else{
            // ip不存在可以访问
            // 将ip添加到redis 中 默认该段时间内第一次访问是第一次访问
            redisUtil.set(key,1,limit.time());
        }
    }
}
