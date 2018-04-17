package com.ljm.server;

import com.ljm.dao.DemoDao;
import com.ljm.domain.Demo;
import com.ljm.repository.DemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Project MyWebProject
 * @ClassName DemoService
 * @Description TODO
 * @Author random
 * @Date Create in 2018/4/2 16:33
 * @Version 1.0
 **/
@Service
public class DemoService {

    @Autowired
    private DemoRepository demoRepository;

    @Autowired
    private DemoDao demoDao;

    public void save(Demo demo) {
        demoRepository.save(demo);
    }

    public Demo getById(Long id) {
        return demoDao.getById(id);
    }

}
