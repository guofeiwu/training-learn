package com.crawer.demo.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Author Mason
 * @Description
 * @Date 2020/5/20 10:35
 **/
@Component
public class HelloCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("HelloCommandLineRunner..." + args);
    }
}
