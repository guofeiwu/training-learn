package com.guofei.wu.springannotation.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Author Mason
 * @Description
 * @Date 2020/1/11 11:14
 **/
public class LastBoss implements InitializingBean, DisposableBean {


    /**
     *     lastboss constructor... have params,name:lastBigBoss
     *     // 属性设置值
     *     postProcessBeforeInitialization,beanName:lastBoss===>LastBoss{name='lastBigBoss'}
     *     jsrInit init ...
     *     InitializingBean afterPropertiesSet....
     *     custom init ...
     *     postProcessAfterInitialization,beanName:lastBoss===>LastBoss{name='lastBigBoss'}
     *     容器初始化...
     *     jsrDestroy destroy ...
     *     DisposableBean destroy....
     *     custom destroy ...
     *     容器关闭...
     */

    /**
     * 先构造函数---》 Bean的后置处理器---》JSR250初始化（postConstruct）---》InitializingBean afterPropertiesSet---》自定义初始化方法
     * ----》 Bean的后置处理器--->容器初始化---》 JSR250销毁---》DisposableBean的销毁---》 自定义销毁方法---》容器关闭
     */


    private String name;

    public LastBoss(String name) {
        System.out.println("lastboss constructor... have params,name:" + name);
        this.name = name;
    }

    public LastBoss() {
        System.out.println("lastboss constructor...not have params");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "LastBoss{" +
                "name='" + name + '\'' +
                '}';
    }


    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean destroy.... ");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean afterPropertiesSet.... ");
    }


    public void customInit() {
        System.out.println("custom init ...");
    }

    public void customDestroy() {
        System.out.println("custom destroy ...");
    }


    @PostConstruct
    public void jsrInit() {
        System.out.println("jsrInit init ...");
    }

    @PreDestroy
    public void jsrDestroy() {
        System.out.println("jsrDestroy destroy ...");
    }
}
