package org.hj.timefilter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class TimefilterApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimefilterApplication.class, args);
    }

}

