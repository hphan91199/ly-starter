package com.ly.dagger.basic;

public class MyCar {

    // See here we create engine right here
    private Engine engine = new Engine();

    public void start() {
        engine.start();
    }

}
