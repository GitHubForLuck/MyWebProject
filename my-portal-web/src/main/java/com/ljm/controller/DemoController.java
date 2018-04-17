package com.ljm.controller;

import com.ljm.domain.Demo;
import com.ljm.server.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project MyWebProject
 * @ClassName DemoController
 * @Description TODO
 * @Author random
 * @Date Create in 2018/4/2 16:35
 * @Version 1.0
 **/
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @RequestMapping("/")
    public String demo() {
        return "demo";
    }

    /**
     * 测试保存数据方法.
     * @return
     */
    @RequestMapping("/save")
    public String save(){
        Demo d = new Demo();
        d.setName("Angel");
        demoService.save(d);//保存数据.
        return"ok.DemoController.save";
    }

    @RequestMapping("/getById")
    public Demo getById() {
        return demoService.getById((long) 1);
    }
}
