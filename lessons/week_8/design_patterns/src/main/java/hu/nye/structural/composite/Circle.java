package hu.nye.structural.composite;

import lombok.Value;

@Value
public class Circle implements Graphic {
    int radius;

    @Override
    public void draw() {
        System.out.println("Drawing a circle of radius " + radius);
    }
}