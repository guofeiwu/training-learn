package com.guofei.wu.designpatterns.vip.factory.func;

import com.guofei.wu.designpatterns.vip.factory.Car;
import com.guofei.wu.designpatterns.vip.factory.FuteCar;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2020-01-05 19:54
 * @since v3.0
 */
public class FuteFactory implements FactoryMethod {
    @Override
    public Car getCar() {
        return new FuteCar();
    }
}
