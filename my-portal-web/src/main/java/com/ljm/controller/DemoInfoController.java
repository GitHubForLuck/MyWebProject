package com.ljm.controller;

import com.ljm.domain.DemoInfo;
import com.ljm.server.DemoInfoService;
import javassist.NotFoundException;
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
        DemoInfo loaded = demoInfoService.findById((long) 1);
        System.out.println("loaded="+loaded);
        DemoInfo cached = demoInfoService.findById((long) 1);
        System.out.println("cached="+cached);
        loaded = demoInfoService.findById((long) 2);
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

    @RequestMapping("/test2")
    @ResponseBody
    public String test2() {
        // 存入两条数据
        DemoInfo demoInfo = new DemoInfo();
        demoInfo.setName("王哈");
        demoInfo.setPwd("123456");
        DemoInfo demoInfo2 = demoInfoService.save(demoInfo);

        // 不走缓存
        System.out.println(demoInfoService.findById2(demoInfo2.getId()));
        // 走缓存
        System.out.println(demoInfoService.findById2(demoInfo2.getId()));

        demoInfo = new DemoInfo();
        demoInfo.setName("李狗");
        demoInfo.setPwd("123456");
        DemoInfo demoInfo3 = demoInfoService.save(demoInfo);

        // 不走缓存
        System.out.println(demoInfoService.findById2(demoInfo3.getId()));
        // 走缓存
        System.out.println(demoInfoService.findById2(demoInfo3.getId()));

        System.out.println("================ 修改数据 ==================");
        // 修改数据
        DemoInfo update = new DemoInfo();
        update.setName("李四-update");
        update.setPwd("123456");
        update.setId(demoInfo3.getId());
        try {
            System.out.println(demoInfoService.update(update));
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        // 不走缓存
        System.out.println(demoInfoService.findById2(update.getId()));

        return "ok";
    }

}
