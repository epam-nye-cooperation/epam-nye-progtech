package hu.nye.progtech.javarecap.exception;

public class UncheckedException {

    public static void main(String[] args) {
        System.out.println("We are not forced to catch it (but we can)");
        exceptionThrower();
    }

    static void exceptionThrower() {
        throw new NullPointerException();
    }

}
