package com.guofei.wu.springannotation.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2020-01-10 22:53
 * @since v3.0
 */
@Component
public class Cat implements InitializingBean, DisposableBean {


    /**
     * cat constructor...
     * cat afterPropertiesSet...
     * 容器初始化...
     * cat destroy...
     * 容器关闭...
     */

    public Cat() {
        System.out.println("cat constructor...");
    }


    @Override
    public void destroy() throws Exception {
        System.out.println("cat destroy...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("cat afterPropertiesSet...");
    }
}
