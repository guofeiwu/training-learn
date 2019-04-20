package com.guofei.wu.weekten.annotation;

import java.util.Arrays;

/**
 * @version 2018/8/8
 * @since 2018/8/8
 */
public class Main {
    public static void main(String... args) {
        A instanceA = new C();
        System.out.println("已使用的@Inherited注解:"+Arrays.toString(instanceA.getClass().getAnnotations()));
        B instanceB = new D();
        System.out.println("已使用的@Inherited注解:"+Arrays.toString(instanceB.getClass().getAnnotations()));
    }
}