package com.ljm.server;

import com.ljm.domain.DemoInfo;
import javassist.NotFoundException;

/**
 * @Project MyWebProject
 * @ClassName DemoInfoService
 * @Description demoInfo 服务接口
 * @Author random
 * @Date Create in 2018/4/8 16:21
 * @Version 1.0
 **/
public interface DemoInfoService {

    DemoInfo findById(Long id);

    void deleteFromCache(Long id);

    void test();

    DemoInfo findById2(Long id);

    void delete(Long id);

    DemoInfo update(DemoInfo update) throws NotFoundException;

    DemoInfo save(DemoInfo demoInfo);

}
