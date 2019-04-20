package com.guofei.wu.weeknine.java8newfeatures;

/**
 * @author Mason
 * @author Mason
 * @version 2018/8/2
 * @since 2018/8/2
 */
public interface DefaultInterface {

    double calculate(int a);


    default double sqrt(int a) {
        return Math.sqrt(a);
    }
}