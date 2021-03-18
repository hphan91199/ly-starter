package com.ly.dagger.dependency_injection_interface;


/**
 * Here are are doing dependencies injection, we create engine outside, then inject it to car by passing it in
 */
public class DemoMain {
    public static void main(String[] args) {

        /**
         * This is called manual dependency injection
         */

        EngineInterface e = new PetrolEngine(); // Create manually and pass to car
        MyCar car1 = new MyCar(e);
        car1.start();

        EngineInterface e2 = new DieselEngine(); // Create manually and pass to car
        MyCar car2 = new MyCar(e2);
        car2.start();

        //Dagger is auto dependency injection
    }
}
