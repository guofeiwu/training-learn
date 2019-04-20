package com.guofei.wu.designpatterns.observer;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Mason
 * @version v1.0
 * @since 2018/12/20
 */
public class AbstractSubject implements Subject {
    Set<Observer> observers = new HashSet<>();

    @Override
    public void add(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void delete(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        if (observers.size() > 0) {
            for (Observer o : observers) {
                o.update();
            }
        }
    }
    @Override
    public void operation() {
    }
}
