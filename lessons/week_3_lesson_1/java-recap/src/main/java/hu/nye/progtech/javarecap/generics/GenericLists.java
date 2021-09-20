package hu.nye.progtech.javarecap.generics;

import java.util.ArrayList;
import java.util.List;

public class GenericLists {

    public static void main(String[] args) {
        // A list that can only contain Strings, checked at compile time
        List<String> strings = new ArrayList<>();
        strings.add("pizza");

        // Adding a number is no further allowed
        // strings.add(2);

        // No need to cast
        String pizza = strings.get(0);

        // ------------------------------------

        // Primitive types are not allowed for generics, we should use wrapper classes: int -> Integer
        List<Integer> numbers = new ArrayList<>();

        // But we can add primitives to the list, they will be autoboxed by Java
        int two = 2;
        numbers.add(two);

        // When getting back the values, they will be unboxed
        int i = numbers.get(0);
    }

}
