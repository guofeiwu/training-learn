package com.guofei.wu.springannotation.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Mason
 * @Description
 * @Date 2020/1/13 9:29
 **/
@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    public void insertUser(User u){
        userDao.insertUser(u);
    }
}
