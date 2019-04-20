package com.guofei.wu.designpatterns.observer;

/**
 * @author Mason
 * @version v1.0
 * @since 2018/12/20
 */
public class ObserverOne implements Observer {
    @Override
    public void update() {
        System.out.println("this is observer one received");
    }
}
