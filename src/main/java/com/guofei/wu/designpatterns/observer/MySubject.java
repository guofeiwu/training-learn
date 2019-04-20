package com.guofei.wu.designpatterns.observer;

/**
 * @author Mason
 * @version v1.0
 * @since 2018/12/20
 */
public class MySubject extends AbstractSubject {
    @Override
    public void operation() {
        System.out.println("operation");
        notifyObservers();
    }
}
