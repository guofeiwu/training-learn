package com.guofei.wu.polymorphism;

/**
 * @author Mason
 * @version v1.0
 * @since 2018/12/21
 */
public class Test {

    public static void main(String[] args) {
        A a = new B();
        a.methodA();
        A a1 = new C();
        a1.methodA();
    }
}
