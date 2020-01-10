package com.guofei.wu.springannotation.bean;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2020-01-10 23:04
 * @since v3.0
 */
@Component
public class Dog {


    /**
     * Dog constructor...
     * dog init ..PostConstruct...
     * 容器初始化...
     * dog destroy ..PreDestroy...
     * 容器关闭...
     */
    public Dog(){
        System.out.println("Dog constructor...");
    }


    @PostConstruct
    public void init(){
        System.out.println("dog init ..PostConstruct...");
    }


    @PreDestroy
    public void destroy(){
        System.out.println("dog destroy ..PreDestroy...");
    }


}
