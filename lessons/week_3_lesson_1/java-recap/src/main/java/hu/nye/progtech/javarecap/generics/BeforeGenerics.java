package hu.nye.progtech.javarecap.generics;

import java.util.ArrayList;
import java.util.List;

public class BeforeGenerics {

    public static void main(String[] args) {
        // Before generics, a List could store Objects
        List objects = new ArrayList();

        // This works, as a String is a subclass of Object
        objects.add("pizza");

        // This works as well, but not intended, as we only want to store Strings
        objects.add(2);

        // We have to cast back to String
        String pizza = (String) objects.get(0);

        // This will cause exception (can't cast int to String)
        String second = (String) objects.get(1);
    }

}
