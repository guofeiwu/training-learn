package com.guofei.wu.designpatterns.observer;

/**
 * @author Mason
 * @version v1.0
 * @since 2018/12/20
 */
public interface Subject {
    void add(Observer observer);

    void delete(Observer observer);

    void notifyObservers();

    void operation();
}
