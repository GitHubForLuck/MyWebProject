package com.ljm.server;

import com.ljm.App;
import com.ljm.server.HelloService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

/**
 * @Project MyWebProject
 * @ClassName HelloServiceTest
 * @Description TODO
 * @Author random
 * @Date Create in 2018/4/4 14:57
 * @Version 1.0
 **/
//// SpringJUnit支持，由此引入Spring-Test框架支持！
@RunWith(SpringJUnit4ClassRunner.class)
//// 指定我们SpringBoot工程的Application启动类
//@SpringApplicationConfiguration(classes = App.class) 这个注解在1.4就被替换了
@SpringBootTest(classes = App.class)
//@SpringBootApplication()
///由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
//@WebAppConfiguration
public class HelloServiceTest {

    @Resource
    private HelloService helloService;

    @Test
    public void testGetName(){
        System.out.println(helloService.getName());
        Assert.assertEquals("hello",helloService.getName());
    }

}
