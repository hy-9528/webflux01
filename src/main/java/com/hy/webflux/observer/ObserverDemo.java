package com.hy.webflux.observer;

import java.util.Observable;

public class ObserverDemo extends Observable {

    public static void main(String[] args) {
        ObserverDemo demo = new ObserverDemo();

        demo.addObserver(((o, arg) -> {
            System.out.println("发生了变化");
        }));

        demo.setChanged();
        demo.notifyObservers();//通知
    }
}
