package hu.nye.progtech.javarecap.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Lists {

    public static void main(String[] args) {
        // List implementation, backed by a dynamically growing array
        ArrayList<String> stringList1 = new ArrayList<>();
        stringList1.add("a");
        stringList1.add("b");

        // Operations that will run in constant time
        stringList1.get(1);
        stringList1.set(1, "c");

        // Print each element of the list, the order will be the same as we added the elements
        Iterator<String> iterator1 = stringList1.iterator();
        while (iterator1.hasNext()) {
            System.out.println(iterator1.next());
        }

        // ------------------------------

        // List implementation backed by a doubly linked list
        LinkedList<String> stringList2 = new LinkedList<>();
        stringList2.add("a");
        stringList2.add("b");

        // Thanks to the linked list implementation, we can add elements to the beginning of the list easily
        stringList2.addFirst("0");

        // These operations are won't work in constant time, the implementation will iterate over the list elements
        stringList2.get(1);
        stringList2.set(1, "c");

        Iterator<String> iterator2 = stringList2.iterator();
        while (iterator2.hasNext()) {
            System.out.println(iterator2.next());
        }
    }

}
