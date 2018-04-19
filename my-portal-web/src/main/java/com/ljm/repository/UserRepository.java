package com.ljm.repository;

import com.ljm.domain.User;

/**
 * @Project MyWebProject
 * @ClassName UserRepository
 * @Description TODO
 * @Author random
 * @Date Create in 2018/4/18 15:54
 * @Version 1.0
 **/
public interface UserRepository extends BaseRepository<User, Long> {
    User findByName(String name);
}
