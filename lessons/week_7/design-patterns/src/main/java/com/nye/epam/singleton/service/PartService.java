package com.nye.epam.singleton.service;

public class PartService {

    private DatabaseService databaseService;

    public PartService() {
        this.databaseService = DatabaseService.getInstance();
    }

    public PartService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }
}
