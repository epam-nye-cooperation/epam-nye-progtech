package hu.nye.progtech.javarecap.collections;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Maps {

    public static void main(String[] args) {
        // A Map to store key-value pairs, backed by a hash table
        Map<String, Integer> stock = new HashMap<>();
        stock.put("monitor", 2);
        // Duplicate keys are not possible, in this case the value related to the key will be updated
        stock.put("monitor", 9);
        stock.put("laptop", 5);
        stock.put("mouse", 1);

        // Get value by key
        Integer monitor = stock.get("monitor");
        System.out.println(monitor);

        // Iterate over the keys and print the related values
        Iterator<String> iterator = stock.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            System.out.println(key + " - " + stock.get(key));
        }

        // Check if our stock contains TV (will be fast, due to hash table implementation)
        boolean containsTv = stock.containsKey("tv");
        System.out.println(containsTv);
    }

}
