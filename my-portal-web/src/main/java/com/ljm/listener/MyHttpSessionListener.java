package com.ljm.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @Project MyWebProject
 * @ClassName MyHttpSessionListener
 * @Description 监听session创建与销毁
 * @Author random
 * @Date Create in 2018/4/4 10:21
 * @Version 1.0
 **/
@WebListener
public class MyHttpSessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        System.out.println("session创建！");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        System.out.println("session销毁！");
    }
}
