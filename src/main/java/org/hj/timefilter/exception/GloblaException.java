package org.hj.timefilter.exception;

import ch.qos.logback.core.net.SocketConnector;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice//(basePackages = "org.hj.timefilter.controller")
public class GloblaException {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map<String,Object> myExceptionHandler(Exception ex){
        Map<String,Object> map  = new HashMap<String,Object>();
        if(ex instanceof MyException){
            MyException myex = (MyException)ex;
            map.put("code",myex.getCode());
            map.put("message",myex.getMessage());
            map.put("method",myex.getMethod());
            map.put("descinfo",myex.getDescinfo());
        }else{
            map.put("errorCode","500");
            map.put("errorMsg", ex.getMessage());
        }
        //发生异常进行日志记录，写入数据库或者其他处理，此处省略
        return map;
    }
}
