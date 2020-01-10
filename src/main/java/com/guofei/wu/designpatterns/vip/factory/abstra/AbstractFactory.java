package com.guofei.wu.designpatterns.vip.factory.abstra;

import com.guofei.wu.designpatterns.vip.factory.Car;

/**
 * @author guofei.wu
 * @version v3.0
 * @date 2020-01-05 19:58
 * @since v3.0
 */
public abstract class AbstractFactory {


    abstract Car getFuteCar();

    abstract Car getBaomaCar();

    abstract Car getDazhongCar();


}
