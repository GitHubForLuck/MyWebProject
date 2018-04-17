package com.ljm.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Project MyWebProject
 * @ClassName SpringUtil2
 * @Description TODO
 * @Author random
 * @Date Create in 2018/4/3 10:08
 * @Version 1.0
 **/
public class SpringUtil3 implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(SpringUtil3.applicationContext == null){
            SpringUtil3.applicationContext  = applicationContext;
        }
        System.out.println("3---------------------------------------------------------------------");
        System.out.println("3---------------------------------------------------------------------");
        System.out.println("3---------------simple.plugin.spring.SpringUtil------------------------------------------------------");
        System.out.println("3========ApplicationContext配置成功,在普通类可以通过调用SpringUtils.getAppContext()获取applicationContext对象,applicationContext="+ SpringUtil3.applicationContext+"========");
        System.out.println("3---------------------------------------------------------------------");
    }

    //获取applicationContext
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    //通过name获取 Bean.
    public static Object getBean(String name){
        return getApplicationContext().getBean(name);
    }

    //通过class获取Bean.
    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    //通过name,以及Clazz返回指定的Bean
    public static <T> T getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }

}
