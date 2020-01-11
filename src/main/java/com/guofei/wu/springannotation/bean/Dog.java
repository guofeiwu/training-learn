package com.guofei.wu.springannotation.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
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
public class Dog implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    /**
     * Dog constructor...
     * dog init ..PostConstruct...
     * 容器初始化...
     * dog destroy ..PreDestroy...
     * 容器关闭...
     */
    public Dog() {
        System.out.println("Dog constructor...");
    }


    @PostConstruct
    public void init() {
        System.out.println("dog init ..PostConstruct...");
    }


    @PreDestroy
    public void destroy() {
        System.out.println("dog destroy ..PreDestroy...");
    }


    /**
     * Bean初始化之前调用BeanPostProcessor的
     * <p>
     * applyBeanPostProcessorsBeforeInitialization
     * 判断Bean是否继承了ApplicationContextAware接口
     * 调用setApplicationContext方法
     *
     * @param applicationContext
     * @throws BeansException
     */

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        System.out.println("applicationContext:" + applicationContext);
    }
}
