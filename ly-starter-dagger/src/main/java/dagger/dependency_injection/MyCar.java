package com.ly.dagger.dependency_injection;

public class MyCar {

    private Engine engine;

    //Here we pass in engine
    public MyCar(Engine enginer) {
        this.engine = enginer;
    }

    public void start() {
        engine.start();
    }

}
