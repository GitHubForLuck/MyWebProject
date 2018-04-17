package com.ljm.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Project MyWebProject
 * @ClassName HelloController
 * @Description TODO
 * @Author random
 * @Date Create in 2018/4/2 15:31
 * @Version 1.0
 **/
@Controller
public class HelloController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello beatiful girls!";
    }

    @RequestMapping("/zeroException")
    @ResponseBody
    public int zeroException(){
        return 100/0;
    }

    // 从 application.properties 中读取配置，如取不到默认值为Hello Shanhy
    @Value("${application.hello:HelloAngel From application}")
    private String hello;

}
