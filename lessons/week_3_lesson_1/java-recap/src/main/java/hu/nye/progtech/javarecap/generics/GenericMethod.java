package hu.nye.progtech.javarecap.generics;

public class GenericMethod {

    public static void main(String[] args) {
        // We want a method, that can only print numbers
        // Instead of overloading a method many times, we can write a generic method
        // We can bound the generic type of the method, so it will only accept a subset of types
        printNumber(2);
        printNumber(2L);
        printNumber(2.4f);
    }

    /*
    static void printNumber(int x) {
        System.out.println(x);
    }

    static void printNumber(float x) {
        System.out.println(x);
    }

    static void printNumber(double x) {
        System.out.println(x);
    }
     */

    static <T extends Number> void printNumber(T x) {
        System.out.println(x);
    }

}
