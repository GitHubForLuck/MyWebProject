package com.ljm.server;

import com.ljm.domain.DemoInfo;

/**
 * @Project MyWebProject
 * @ClassName DemoInfoService
 * @Description demoInfo 服务接口
 * @Author random
 * @Date Create in 2018/4/8 16:21
 * @Version 1.0
 **/
public interface DemoInfoService {

    DemoInfo findById(long id);

    void deleteFromCache(long id);

    void test();

}
