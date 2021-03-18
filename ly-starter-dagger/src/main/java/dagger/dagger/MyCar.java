package com.ly.dagger.dagger;

import javax.inject.Inject;

public class MyCar {
    private Engine engine;
    private Wheel wheel;

    @Inject
    //Inject mean saying this is how you create the object of these when component call for it
    public MyCar(Engine e, Wheel w) {
        engine = e;
        wheel = w;
    }

    public void start() {
        System.out.println("Driving...");
    }
}
