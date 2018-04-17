package com.ljm.controller;

import com.ljm.domain.DemoInfo;
import com.ljm.server.DemoInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Project MyWebProject
 * @ClassName DemoInfoController
 * @Description TODO
 * @Author random
 * @Date Create in 2018/4/8 16:26
 * @Version 1.0
 **/
@Controller
public class DemoInfoController {

    @Autowired
    DemoInfoService demoInfoService;

    @RequestMapping("/testCache")
    @ResponseBody
    public String test(){
        DemoInfo loaded = demoInfoService.findById(1);
        System.out.println("loaded="+loaded);
        DemoInfo cached = demoInfoService.findById(1);
        System.out.println("cached="+cached);
        loaded = demoInfoService.findById(2);
        System.out.println("loaded2="+loaded);
        return"ok";
    }


    @RequestMapping("/delete")
    public@ResponseBody String delete(long id){
        demoInfoService.deleteFromCache(id);
        return"ok";
    }

    @RequestMapping("/test1")
    public@ResponseBody String test1(){
        demoInfoService.test();
        System.out.println("DemoInfoController.test1()");
        return"ok";
    }

}
