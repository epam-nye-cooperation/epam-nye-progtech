package com.nye.epam.singleton.service;

public class CarService {

    private DatabaseService databaseService;

    public CarService() {
        databaseService = DatabaseService.getInstance();
    }

    public CarService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }
}
