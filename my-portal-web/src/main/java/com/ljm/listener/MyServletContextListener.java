package com.ljm.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @Project MyWebProject
 * @ClassName MyServletContextListener
 * @Description 使用@WebListener注解，实现ServletContextListener接口
 * @Author random
 * @Date Create in 2018/4/4 10:18
 * @Version 1.0
 **/
@WebListener
public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("servletContext初始化！");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("servletContext销毁！");
    }
}
