package com.ljm.dao;

import com.ljm.domain.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * @Project MyWebProject
 * @ClassName DemoDao
 * @Description TODO
 * @Author random
 * @Date Create in 2018/4/2 16:54
 * @Version 1.0
 **/
@Repository
public class DemoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 通过id获取demo对象.
     * @param id
     * @return
     */
    public Demo getById(long id){
        String sql = "select*from Demo where id=?";
        RowMapper<Demo> rowMapper =new BeanPropertyRowMapper<Demo>(Demo.class);
        return jdbcTemplate.queryForObject(sql, rowMapper,id);
    }

}
