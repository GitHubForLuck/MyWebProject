package com.ljm.controller;

import com.ljm.config.Wisely2Settings;
import com.ljm.config.WiselySettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Project MyWebProject
 * @ClassName TestController
 * @Description TODO
 * @Author random
 * @Date Create in 2018/4/4 14:30
 * @Version 1.0
 **/
@Controller
public class TestController {

    @Autowired
    WiselySettings wiselySettings;

    @Autowired
    Wisely2Settings wisely2Settings;

    @RequestMapping("/test")
    public @ResponseBody String test(){
        System.out.println(wiselySettings.getGender()+"---"+wiselySettings.getName());
        System.out.println(wisely2Settings.getGender()+"==="+wisely2Settings.getName());
        return "ok";
     }

}
