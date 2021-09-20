package hu.nye.progtech.javarecap.exception;

public class CustomException {

    public static void main(String[] args) {
        System.out.println("We can throw our custom exception");
        exceptionThrower();
    }

    static void exceptionThrower() {
        throw new MyException();
    }

    static class MyException extends RuntimeException {
    }

}
