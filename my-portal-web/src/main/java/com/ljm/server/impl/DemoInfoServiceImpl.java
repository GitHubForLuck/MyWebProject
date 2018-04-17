package com.ljm.server.impl;

import com.ljm.domain.DemoInfo;
import com.ljm.repository.DemoInfoRepository;
import com.ljm.server.DemoInfoService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Project MyWebProject
 * @ClassName DemoInfoServiceImpl
 * @Description DemoInfo数据处理类
 * @Author random
 * @Date Create in 2018/4/8 16:23
 * @Version 1.0
 **/
@Service
public class DemoInfoServiceImpl implements DemoInfoService {

    @Resource
    private DemoInfoRepository demoInfoRepository;

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public void test(){
        ValueOperations<String,String> valueOperations =redisTemplate.opsForValue();
        valueOperations.set("mykey4", "random1="+Math.random());
        System.out.println(valueOperations.get("mykey4"));
    }

    //keyGenerator="myKeyGenerator"
    @Cacheable(value="demoInfo") //缓存,这里没有指定key.
    @Override
    public DemoInfo findById(long id) {
        System.err.println("DemoInfoServiceImpl.findById()=========从数据库中进行获取的....id="+id);
        return demoInfoRepository.findOne(id);
    }

    @CacheEvict(value="demoInfo")
    @Override
    public void deleteFromCache(long id) {
        System.out.println("DemoInfoServiceImpl.delete().从缓存中删除.");
    }

}
