package hu.nye.progtech.javarecap.collections;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class Sets {

    public static void main(String[] args) {
        // Set implementation, backed by a hash table (a HashMap is used actually)
        Set<String> stringSet1 = new HashSet<>();
        // A set won't contain duplicates
        stringSet1.add("z");
        stringSet1.add("a");
        stringSet1.add("a");
        stringSet1.add("b");

        // Thanks to the hash table, checking if the set already contains a certain element is fast
        System.out.println(stringSet1.contains("a"));

        // Due to the hash table implementation, the order of the elements won't match the order how they were added
        Iterator<String> iterator1 = stringSet1.iterator();
        while (iterator1.hasNext()) {
            System.out.println(iterator1.next());
        }

        // --------------------------------

        Set<String> stringSet2 = new LinkedHashSet<>();
        stringSet2.add("z");
        stringSet2.add("a");
        stringSet2.add("b");

        // A LinkedHashSet will hold the order of the elements
        Iterator<String> iterator2 = stringSet2.iterator();
        while (iterator2.hasNext()) {
            System.out.println(iterator2.next());
        }
    }

}
