package com.ljm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @Project MyWebProject
 * @ClassName RedisSessionConfig
 * @Description 相关修改：
 *                                        application.properties修改redis配置信息（请自行安装redis，主要修改spring.redis.host）
 *                                        所有实体类实现Serializable接口
 *                         注意事项：
 *                                        redis版本号需要是2.8以上否则会抛异常：ERR Unsupported CONFIG parameter: notify-keyspace-events；
 *                                        RedisSessionConfig需要放在App.java启动类可以扫描的位置；
 * @Author random
 * @Date Create in 2018/4/18 11:42
 * @Version 1.0
 **/
@Configuration
@EnableRedisHttpSession
//@EnableRedisHttpSession(maxInactiveIntervalInSeconds= 60) //1分钟失效
public class RedisSessionConfig {
}
