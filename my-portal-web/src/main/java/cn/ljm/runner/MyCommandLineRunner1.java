package cn.ljm.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

/**
 * @Project MyWebProject
 * @ClassName MyCommandLineRunner1
 * @Description TODO
 * @Author random
 * @Date Create in 2018/4/4 14:46
 * @Version 1.0
 **/
@Configuration
public class MyCommandLineRunner1 implements CommandLineRunner {
    @Override
    public void run(String... strings) throws Exception {
        System.out.println("MyCommandLineRunner1.run()");
    }
}
