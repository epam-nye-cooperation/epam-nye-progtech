package hu.nye.progtech.javarecap.exception;

import java.io.IOException;

public class CheckedException {

    public static void main(String[] args) {
        try {
            exceptionThrower();
        } catch (IOException e) {
            System.out.println("Caught checked exception");
        }
    }

    static void exceptionThrower() throws IOException {
        throw new IOException();
    }

}
