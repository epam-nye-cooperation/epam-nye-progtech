package com.nye.epam.builder;

public class Owner {
    String name;

    public Owner(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Owner{" +
            "name='" + name + '\'' +
            '}';
    }
}
