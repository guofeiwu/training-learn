package com.guofei.wu.jvm;

/**
 * @author Mason
 * @version v1.0
 * @since 2019/3/5
 */
public class NotInitialization {

    public static void main(String[] args) {
        SuperClass superClass = new SubClass();
    }
}
