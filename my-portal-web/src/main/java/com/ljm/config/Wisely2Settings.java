package com.ljm.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Project MyWebProject
 * @ClassName Wisely2Settings
 * @Description TODO
 * @Author random
 * @Date Create in 2018/4/4 14:14
 * @Version 1.0
 **/
@ConfigurationProperties(prefix = "wisely2")
public class Wisely2Settings {

    private String name;
    private String gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
