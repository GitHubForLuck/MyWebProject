package com.ljm.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

/**
 * @Project MyWebProject
 * @ClassName BaseRepository
 * @Description TODO
 * @Author random
 * @Date Create in 2018/4/18 15:52
 * @Version 1.0
 **/
@NoRepositoryBean
public interface BaseRepository<T, I extends Serializable> extends PagingAndSortingRepository<T, I>, JpaSpecificationExecutor<T> {
}
