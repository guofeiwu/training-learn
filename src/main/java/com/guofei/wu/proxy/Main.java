package com.guofei.wu.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2019-06-12 14:43
 * @since v3.0
 */
public class Main {


    public static void main(String[] args) {
        Subject subject = new SubjectImpl();
        InvocationHandler ih = new MyInvocationHandler(subject);


        Subject o = (Subject) Proxy.newProxyInstance(SubjectImpl.class.getClassLoader(), SubjectImpl.class.getInterfaces(), ih);


        o.sayHello("Mason");

        ProxyUtils.generateClassFile(SubjectImpl.class, "SubjectProxy");


    }
}
