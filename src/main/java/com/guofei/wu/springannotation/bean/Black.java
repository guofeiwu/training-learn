package com.guofei.wu.springannotation.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2020-01-10 21:49
 * @since v3.0
 */
// @Component
public class Black {
    // @Autowired
    private AutowiredAnimal autowiredAnimal;

    public void print() {
        System.out.println("autowiredAnimal====>>" + autowiredAnimal);
    }
}
