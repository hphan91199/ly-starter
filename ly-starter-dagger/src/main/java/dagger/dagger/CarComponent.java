package dagger.dagger;

import dagger.Component;

@Component
public interface CarComponent {

    public com.ly.dagger.dagger.MyCar getCar();

    //Dagger see MyCar and will auto implement MyCar o = new MyCar();
}
