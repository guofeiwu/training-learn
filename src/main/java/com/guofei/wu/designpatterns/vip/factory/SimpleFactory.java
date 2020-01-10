package com.guofei.wu.designpatterns.vip.factory;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2020-01-05 19:41
 * @since v3.0
 */
public class SimpleFactory {


    public static Car getCarByName(String name) {
        if ("福特".equals(name)) {
            return new FuteCar();
        } else if ("大众".equals(name)) {
            return new Dazhong();
        } else if ("宝马".equals(name)) {
            return new Baoma();
        } else {
            throw new IllegalArgumentException("暂无此Car");
        }
    }


}
