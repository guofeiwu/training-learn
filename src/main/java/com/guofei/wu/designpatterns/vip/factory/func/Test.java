package com.guofei.wu.designpatterns.vip.factory.func;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2020-01-05 19:57
 * @since v3.0
 */
public class Test {


    public static void main(String[] args) {
        FactoryMethod factory = new BaomaFactory();
        System.out.println(factory.getCar().getCar());

         factory = new FuteFactory();
        System.out.println(factory.getCar().getCar());
    }
}
