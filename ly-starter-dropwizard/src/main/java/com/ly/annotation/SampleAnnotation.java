package com.ly.annotation;

import java.lang.annotation.*;

/**
 * Example of custom annotation and usage
 */

@Target(ElementType.TYPE) //To use for class
@Retention(RetentionPolicy.RUNTIME)
@interface Dog {
    String colour() default "Transparent";

    int age() default 0;
}

/**
 * Any instance of this JapaneseSpitz class will also have property of colour white and age 1
 */
@Dog(colour = "White", age = 1)
class JapaneseSpitz {
    String name;
    int cuteScore;

    public JapaneseSpitz(String n, int c) {
        name = n;
        cuteScore = c;
    }

    @Override
    public String toString() {
        return "Name = " + name + " cuteScore = " + cuteScore;
    }
}


public class SampleAnnotation {

    public static void main(String[] args) {
        System.out.println("Hello World");

        JapaneseSpitz capi = new JapaneseSpitz("Capi", 100);

        System.out.println(capi);

        Annotation an = capi.getClass().getAnnotation(Dog.class);
        Dog aDog = (Dog) an;
        System.out.println(an);
        System.out.println(aDog.age());

    }

}
