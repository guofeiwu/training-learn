package com.crawer.demo.spring;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @Author Mason
 * @Description
 * @Date 2020/5/20 10:33
 **/
@Component
public class HelloApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("HelloApplicationRunner...." + args);
    }
}
