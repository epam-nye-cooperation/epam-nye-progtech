package com.nye.epam.singleton.service;

import java.util.Objects;

public class DatabaseService {

    private static DatabaseService instance;

    public static DatabaseService getInstance() {
        System.out.println("Get Instance Running");
        if (Objects.isNull(instance)) {
            instance = new DatabaseService();
        }
        return instance;
    }

    private DatabaseService() {
        System.out.println("Database Connection Created!");
    }
}
