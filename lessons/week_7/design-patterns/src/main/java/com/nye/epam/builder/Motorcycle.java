package com.nye.epam.builder;

import lombok.Builder;
import lombok.NonNull;

@Builder
public class Motorcycle {
    private int yearOfManufacturing;
    @NonNull
    private String brandName;
    private String modelName;
    private String color;
    @Builder.Default private Owner owner = new Owner("EPAM");

    @Override
    public String toString() {
        return "Motorcycle{" +
            "yearOfManufacturing=" + yearOfManufacturing +
            ", brandName='" + brandName + '\'' +
            ", modelName='" + modelName + '\'' +
            ", color='" + color + '\'' +
            ", owner=" + owner +
            '}';
    }
}
