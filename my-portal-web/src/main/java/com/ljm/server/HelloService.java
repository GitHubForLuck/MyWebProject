package com.ljm.server;

import org.springframework.stereotype.Service;

/**
 * @Project MyWebProject
 * @ClassName HelloService
 * @Description TODO
 * @Author random
 * @Date Create in 2018/4/4 14:54
 * @Version 1.0
 **/
@Service
public class HelloService {

    public String getName() {
        return "hello";
    }

}
