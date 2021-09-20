package hu.nye.progtech.javarecap.generics;

public class CustomGenericClass {

    public static void main(String[] args) {
        // Our own generic class in action
        Box<Double> doubleBox = new Box<>();
        doubleBox.setValue(3.14);

        double d = doubleBox.getValue();
        System.out.println(d);
    }

}
