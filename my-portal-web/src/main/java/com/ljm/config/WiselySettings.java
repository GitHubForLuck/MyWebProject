package com.ljm.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Project MyWebProject
 * @ClassName WiselySettings
 * @Description TODO
 * @Author random
 * @Date Create in 2018/4/4 14:18
 * @Version 1.0
 **/
@Component      // @ConfigurationProperties的location属性被取消
@ConfigurationProperties(prefix = "wisely")
@PropertySource("classpath:wisely.properties")
public class WiselySettings {

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
