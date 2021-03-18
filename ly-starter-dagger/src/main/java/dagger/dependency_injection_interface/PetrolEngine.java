package com.ly.dagger.dependency_injection_interface;

public class PetrolEngine implements EngineInterface {

    @Override
    public void start() {
        System.out.println("Petrol Engine Started...");
    }
}
