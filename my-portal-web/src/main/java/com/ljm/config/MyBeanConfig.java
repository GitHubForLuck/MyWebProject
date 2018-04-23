package com.ljm.config;

import com.ljm.domain.Shanhy;
import com.ljm.server.HelloService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Project MyWebProject
 * @ClassName MyBeanConfig
 * @Description TODO
 * @Author random
 * @Date Create in 2018/4/23 10:02
 * @Version 1.0
 **/
@Configuration
public class MyBeanConfig {

    /**
     *  这里只是测试使用，没有实际的意义.
     */
    @Bean(name="testHelloService")
    public HelloService filterRegistrationBean(@Qualifier("shanhyB") Shanhy shanhy){
        HelloService helloService = new HelloService();
        shanhy.display();
        // 其它处理代码.
        return helloService;
    }

}
