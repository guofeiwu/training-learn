package com.crawer.demo.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @Author Mason
 * @Description
 * @Date 2020/5/20 10:31
 **/
public class HelloSpringApplicationRunListener implements SpringApplicationRunListener {

    public HelloSpringApplicationRunListener(SpringApplication application, String[] args){

    }

    @Override
    public void starting() {
        System.out.println(" HelloApplicationListener ... starting");
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {

        System.out.println(" HelloApplicationListener ... environmentPrepared" + environment.getProperty("os.name"));

    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        System.out.println(" HelloApplicationListener ... contextPrepared");

    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        System.out.println(" HelloApplicationListener ... contextLoaded");

    }

    @Override
    public void started(ConfigurableApplicationContext context) {
        System.out.println(" HelloApplicationListener ... started");

    }

    @Override
    public void running(ConfigurableApplicationContext context) {
        System.out.println(" HelloApplicationListener ... running");

    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        System.out.println(" HelloApplicationListener ... failed");

    }
}
