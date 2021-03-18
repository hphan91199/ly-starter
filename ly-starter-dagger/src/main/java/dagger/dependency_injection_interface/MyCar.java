package com.ly.dagger.dependency_injection_interface;

public class MyCar {

    /**
     * Without dependency injection, we can use this car class to create either pertrol engine or diesel engine
     * <p>
     * private Engine engine = new PetrolEngine();
     * Or this
     * private Engine engine = new DieselEngine();
     * <p>
     * But with dependency injection, we can use this class with any type of engine
     * We only care we need engine, not PetrolEngine or DieselEngine
     */

    public EngineInterface engine;

    public MyCar(EngineInterface e) {
        engine = e;
    }

    public void start() {
        engine.start();
    }

}
