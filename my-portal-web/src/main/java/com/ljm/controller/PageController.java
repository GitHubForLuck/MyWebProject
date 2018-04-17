package com.ljm.controller;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;

/**
 * @Project MyWebProject
 * @ClassName PageController
 * @Description TODO
 * @Author random
 * @Date Create in 2018/4/4 11:01
 * @Version 1.0
 **/
@Controller
public class PageController implements EnvironmentAware {

    // @Controller@Service 等被Spring管理的类都支持，注意重写的方法setEnvironment 是在系统启动的时候被执行。
    @Override
    public void setEnvironment(Environment environment) {
        String s= environment.getProperty("JAVA_HOME");
        System.out.println("PageController:" + s);
    }
}
