package com.guofei.wu.weeknine.java8newfeatures;


/**
 * @author Mason
 * @author Mason
 * @version 2018/8/2
 * @since 2018/8/2
 */

@FunctionalInterface
public interface FunctionalInterfaces<k, T> {
    T convert(k k);
}
