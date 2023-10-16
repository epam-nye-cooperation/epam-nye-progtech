package com.nye.epam.builder;

import com.nye.epam.builder.car.CarBuilder;

public class Main {

    public static void main(String[] args) {
        CarBuilder carBuilder = new CarBuilder();

        System.out.println(carBuilder.setYearOfManufacturing(2022)
            .setFuelType(Fuel.ELECTRIC)
            .setModelName("Model 3")
            .setBrandName("Tesla")
            .setTransmissionType(Transmission.AUTOMATIC).build()
        );

        System.out.println(Motorcycle.builder()
            .yearOfManufacturing(2020)
            .brandName("BrandName")
            .modelName("ModelName")
            .color("black")
            .owner(new Owner("Kornel Filep"))
            .build()
        );
    }

}
