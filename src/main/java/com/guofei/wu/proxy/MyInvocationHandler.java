package com.guofei.wu.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2019-06-12 14:39
 * @since v3.0
 */
public class MyInvocationHandler implements InvocationHandler {

    Object object;

    public MyInvocationHandler(Object o) {
        this.object = o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("args " + Arrays.toString(args));

        System.out.println("start invoke");

        Object invoke = method.invoke(object, args);

        System.out.println("end invoke");

        return invoke;
    }
}
