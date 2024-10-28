package hu.nye.structural.composite;

import lombok.Value;

@Value
public class Square implements Graphic {
    int size;

    @Override
    public void draw() {
        System.out.println("Drawing a square of size " + size);
    }
}
