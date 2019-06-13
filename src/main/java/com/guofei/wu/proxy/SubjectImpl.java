package com.guofei.wu.proxy;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2019-06-12 14:38
 * @since v3.0
 */
public class SubjectImpl implements Subject {

    @Override
    public void sayHello(String name) {
        System.out.println("Hello " + name);
    }
}
