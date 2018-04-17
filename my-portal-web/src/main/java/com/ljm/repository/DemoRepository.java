package com.ljm.repository;

import com.ljm.domain.Demo;
import org.springframework.data.repository.CrudRepository;

/**
 * @Project MyWebProject
 * @ClassName DemoRepository
 * @Description TODO
 * @Author random
 * @Date Create in 2018/4/2 16:27
 * @Version 1.0
 **/
public interface DemoRepository extends CrudRepository<Demo, Long> {
}
