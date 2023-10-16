package com.nye.epam.builder.car;

import com.nye.epam.builder.Fuel;
import com.nye.epam.builder.Owner;
import com.nye.epam.builder.Transmission;

public class CarBuilder {
    private int yearOfManufacturing;
    private String brandName;
    private String modelName;
    private String color;
    private int numberOfDoors = 4;
    private Fuel fuelType = Fuel.GAS;
    private Transmission transmissionType = Transmission.MANUAL;
    private Owner owner = new Owner("EPAM");

    public CarBuilder setYearOfManufacturing(int yearOfManufacturing) {
        this.yearOfManufacturing = yearOfManufacturing;
        return this;
    }

    public CarBuilder setBrandName(String brandName) {
        this.brandName = brandName;
        return this;
    }

    public CarBuilder setModelName(String modelName) {
        this.modelName = modelName;
        return this;
    }

    public CarBuilder setColor(String color) {
        this.color = color;
        return this;
    }

    public CarBuilder setNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
        return this;
    }

    public CarBuilder setFuelType(Fuel fuelType) {
        this.fuelType = fuelType;
        return this;
    }

    public CarBuilder setTransmissionType(Transmission transmissionType) {
        this.transmissionType = transmissionType;
        return this;
    }

    public CarBuilder setOwner(Owner owner) {
        this.owner = owner;
        return this;
    }

    public Car build() {
        if (this.yearOfManufacturing == 0) {
            throw new RuntimeException("yearOfManufacturing is mandatory for Cars");
        }

        return new Car(this.yearOfManufacturing, this.brandName, this.modelName, this.color, this.numberOfDoors,
            this.fuelType,
            this.transmissionType, this.owner);
    }
}
