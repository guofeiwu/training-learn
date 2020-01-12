package com.guofei.wu.springannotation.service;

import com.guofei.wu.springannotation.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.inject.Inject;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2020-01-10 20:25
 * @since v3.0
 */
@Service
public class BookService {

    //    @Qualifier(value = "bookDao")
    @Autowired
//    @Resource(name = "bookDao2")
//    @Inject
    private BookDao bookDao;


    public void print() {
        System.out.println("bookDao:" + bookDao);
    }
}
