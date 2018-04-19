package com.ljm.config;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;

/**
 * @Project MyWebProject
 * @ClassName CacheConfiguration
 * @Description 缓存配置
 * @Author random
 * @Date Create in 2018/4/8 18:00
 * @Version 1.0
 **/
@Configuration
@EnableCaching //标注启动缓存.
public class CacheConfiguration {

    /**
     *  ehcache主要的管理器
     *  学习spring boot 时整合了redis再整合ehcahe是报
     *  No CacheResolver specified, and no unique bean of type CacheManager found的错,
     *  CacheManager 不唯一,各种百度之后发现是要在一个RedisCacheConfig的CacheManager 上加上@Primary，或CacheConfiguration的EhCacheCacheManager上加上@Primary
     * @param bean
     * @return
     */
    @Primary
    @Bean
    public EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean bean){
        System.out.println("CacheConfiguration.ehCacheCacheManager()");
        return new EhCacheCacheManager(bean.getObject());
    }

    /*
       * 据shared与否的设置,
       * Spring分别通过CacheManager.create()
       * 或new CacheManager()方式来创建一个ehcache基地.
       *
       * 也说是说通过这个来设置cache的基地是这里的Spring独用,还是跟别的(如hibernate的Ehcache共享)
       *
       */
    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean(){
        System.out.println("CacheConfiguration.ehCacheManagerFactoryBean()");
        EhCacheManagerFactoryBean cacheManagerFactoryBean =new EhCacheManagerFactoryBean ();
        cacheManagerFactoryBean.setConfigLocation (new ClassPathResource("conf/ehcache.xml"));
        cacheManagerFactoryBean.setShared(true);
        return cacheManagerFactoryBean;
    }

}
