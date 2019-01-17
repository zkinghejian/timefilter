package org.hj.timefilter.controller;

import org.hj.timefilter.anno.RequestLimit;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TestController {


    @RequestMapping(value = "/testip/{id}",method = RequestMethod.GET)
    @RequestLimit(count = 1)
    @ResponseBody
    public String test(HttpServletRequest request, @PathVariable Integer id){
        int i = 10/id;
        //System.out.println("--------------------");
        return "hello";
    }
}
