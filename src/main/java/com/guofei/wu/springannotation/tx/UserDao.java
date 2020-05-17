package com.guofei.wu.springannotation.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Author Mason
 * @Description
 * @Date 2020/1/13 9:22
 **/
@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void insertUser(User user) {
        String sql = "insert  into bedroom_building_manage(building_name,teacher_id,latest_close_door_time,feature,school_id,status,gmt_create,gmt_modify) " +
                "values (?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, user.getName(), 1, "12:23", "", 1, 0, new Date(), new Date());
        System.out.println("插入完成。。。");
        int j = 10 / 0;
    }
}
