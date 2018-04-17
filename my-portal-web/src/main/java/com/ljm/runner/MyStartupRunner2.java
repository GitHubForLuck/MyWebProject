package com.ljm.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Project MyWebProject
 * @ClassName MyStartupRunner2
 * @Description TODO
 * @Author random
 * @Date Create in 2018/4/4 10:40
 * @Version 1.0
 **/
@Component
@Order(value = 1)
public class MyStartupRunner2 implements CommandLineRunner {
    @Override
    public void run(String... strings) throws Exception {
        System.out.println(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作 22222222<<<<<<<<<<<<<");
    }
}
