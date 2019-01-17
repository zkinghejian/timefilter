package org.hj.timefilter.anno;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
//使用order属性，设置该类在spring容器中的加载顺序
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface RequestLimit {
    // 默认访问次数
    int count() default Integer.MAX_VALUE;
    // 默认一分钟访问
    long time() default 60;
}
