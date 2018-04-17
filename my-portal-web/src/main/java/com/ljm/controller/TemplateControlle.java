package com.ljm.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @Project MyWebProject
 * @ClassName TemplateControlle
 * @Description 模板测试
 * @Author random
 * @Date Create in 2018/4/3 10:23
 * @Version 1.0
 **/
@Controller
public class TemplateControlle {

    /**
     * 返回html模板.
     */
    @RequestMapping("/helloHtml")
    public String helloHtml(Map<String,Object> map){
        map.put("hello","fromTemplateController.helloHtml");
        return"/helloHtml";
    }

    /**
     * 返回html模板.
     */
    @RequestMapping("/helloFtl")
    public String helloFtl(Map<String,Object> map){
        map.put("hello","fromTemplateController.helloFtl");
        return "/helloFtl";
    }

    // 从 application.properties 中读取配置，如取不到默认值为Hello Shanhy
    @Value("${application.hello:HelloAngel From application}")
    private String hello;


    @RequestMapping("/helloJsp")
    public String helloJsp(Map<String,Object> map){
        System.out.println("HelloController.helloJsp().hello="+hello);
        map.put("hello", hello);
        return "helloJsp";
    }

}
