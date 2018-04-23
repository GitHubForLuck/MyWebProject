package com.ljm.controller;

import com.ljm.domain.Shanhy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Project MyWebProject
 * @ClassName MyBeanController
 * @Description TODO
 * @Author random
 * @Date Create in 2018/4/23 10:08
 * @Version 1.0
 **/
@Controller
public class MyBeanController {

    @Resource(name = "shanhyA")
    private Shanhy shanhyA;

    @Autowired
    @Qualifier("shanhyB")
    private Shanhy shanhyB;

    @RequestMapping("/testMyBean")
    @ResponseBody
    public String test(){
        shanhyA.display();
        shanhyB.display();
        return"testddd";
    }

}
