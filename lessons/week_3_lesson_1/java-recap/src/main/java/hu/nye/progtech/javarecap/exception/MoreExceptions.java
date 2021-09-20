package hu.nye.progtech.javarecap.exception;

public class MoreExceptions {

    public static void main(String[] args) {
        try {
            exceptionThrower();
        } catch (NullPointerException | ArithmeticException e) {
            System.out.println("Union catch block");
        } finally {
            System.out.println("Last operation, runs even if we don't catch any exception");
        }
    }

    static void exceptionThrower() {
        throw new ArithmeticException();
    }

}
