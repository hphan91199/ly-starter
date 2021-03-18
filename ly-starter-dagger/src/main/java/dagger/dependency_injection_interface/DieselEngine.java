package com.ly.dagger.dependency_injection_interface;

public class DieselEngine implements EngineInterface {

    @Override
    public void start() {
        System.out.println("Diesel Engine Started...");
    }
}
