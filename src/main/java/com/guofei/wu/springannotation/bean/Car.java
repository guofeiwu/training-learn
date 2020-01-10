package com.guofei.wu.springannotation.bean;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2020-01-10 22:38
 * @since v3.0
 */
public class Car {
    public Car() {
        System.out.println("car ... constructor...");
    }

    public void init() {
        System.out.println("car ... init...");
    }

    public void destroy() {
        System.out.println("car ... destory...");
    }
}
