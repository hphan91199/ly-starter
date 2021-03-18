package com.ly.dagger.dependency_injection;


/**
 * Here are are doing dependencies injection, we create engine outside, then inject it to car by passing it in
 */
public class DemoMain {
    public static void main(String[] args) {
        Engine e = new Engine();
        MyCar car1 = new MyCar(e);
        car1.start();
    }
}
