package com.ly.dagger.dagger;

@Component
public interface CarComponent {

    public MyCar getCar();

    //Dagger see MyCar and will auto implement MyCar o = new MyCar();
}
