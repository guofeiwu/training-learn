package com.guofei.wu.designpatterns.vip.factory.abstra;

import com.guofei.wu.designpatterns.vip.factory.Car;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2020-01-05 20:00
 * @since v3.0
 */
public class ConcreteFactory extends AbstractFactory {
    @Override
    Car getFuteCar() {
        return null;
    }

    @Override
    Car getBaomaCar() {
        return null;
    }

    @Override
    Car getDazhongCar() {
        return null;
    }
}
