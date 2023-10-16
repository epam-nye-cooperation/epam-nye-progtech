package com.nye.epam.builder.car;

import com.nye.epam.builder.Fuel;
import com.nye.epam.builder.Owner;
import com.nye.epam.builder.Transmission;

public class Car {
    private int yearOfManufacturing;
    private String brandName;
    private String modelName;
    private String color;
    private int numberOfDoors;
    private Fuel fuelType;
    private Transmission transmissionType;
    private Owner owner;

    Car(int yearOfManufacturing, String brandName, String modelName, String color, int numberOfDoors,
        Fuel fuelType,
        Transmission transmissionType, Owner owner) {
        this.yearOfManufacturing = yearOfManufacturing;
        this.brandName = brandName;
        this.modelName = modelName;
        this.color = color;
        this.numberOfDoors = numberOfDoors;
        this.fuelType = fuelType;
        this.transmissionType = transmissionType;
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Car{" +
            "yearOfManufacturing=" + yearOfManufacturing +
            ", brandName='" + brandName + '\'' +
            ", modelName='" + modelName + '\'' +
            ", color='" + color + '\'' +
            ", numberOfDoors=" + numberOfDoors +
            ", fuelType=" + fuelType +
            ", transmissionType=" + transmissionType +
            ", owner=" + owner +
            '}';
    }
}
