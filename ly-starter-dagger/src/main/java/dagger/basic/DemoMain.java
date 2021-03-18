package com.ly.dagger.basic;


/**
 * Here we create engine object when we create car, tightly coupled
 */
public class DemoMain {
    public static void main(String[] args) {
        MyCar car1 = new MyCar();
        car1.start();
    }
}
