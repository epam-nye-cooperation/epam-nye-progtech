package com.nye.epam.singleton.service;

public class EmployeeService {

    private DatabaseService databaseService;

    public EmployeeService() {
        this.databaseService = DatabaseService.getInstance();
    }

    public EmployeeService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }
}
