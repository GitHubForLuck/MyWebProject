package org.ljm.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

/**
 * @Project MyWebProject
 * @ClassName MyCommandLineRunner2
 * @Description TODO
 * @Author random
 * @Date Create in 2018/4/4 14:47
 * @Version 1.0
 **/
@Configuration
public class MyCommandLineRunner2 implements CommandLineRunner {
    @Override
    public void run(String... strings) throws Exception {
        System.out.println("MyCommandLineRunner2.run()");
    }
}
