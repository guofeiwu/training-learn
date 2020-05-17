package com.guofei.wu.designpatterns.observer;

public class ObserverTest {

    public static void main(String[] args) {
        Subject sub = new MySubject();
        sub.add(new ObserverOne());
        sub.add(new ObserverTwo());

        sub.operation();
    }

}
