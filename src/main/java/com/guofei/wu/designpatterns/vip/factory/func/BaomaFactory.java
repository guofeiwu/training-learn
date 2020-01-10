package com.guofei.wu.designpatterns.vip.factory.func;

import com.guofei.wu.designpatterns.vip.factory.Baoma;
import com.guofei.wu.designpatterns.vip.factory.Car;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2020-01-05 19:54
 * @since v3.0
 */
public class BaomaFactory implements FactoryMethod {
    @Override
    public Car getCar() {
        return new Baoma();
    }
}
