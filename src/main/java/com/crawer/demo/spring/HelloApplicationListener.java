package com.crawer.demo.spring;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @Author Mason
 * @Description
 * @Date 2020/5/20 10:48
 **/
public class HelloApplicationListener implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        System.out.println("HelloApplicationListener...onApplicationEvent" + applicationEvent);
    }
}
