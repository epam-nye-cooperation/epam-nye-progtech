package hu.nye.progtech.di;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        UserServiceWithoutDI userServiceWithoutDI = new UserServiceWithoutDI();
        List<User> userList = userServiceWithoutDI.findAll();
        System.out.println(userList);

        // ----------------------------------

        Map<Integer, User> userMap = new HashMap<>();
        UserRepository userRepository = new UserRepository(userMap);
        UserServiceWithDI userServiceWithDI = null;

        // constructor based
        userServiceWithDI = new UserServiceWithDI(userRepository);
        List<User> userList1 = userServiceWithDI.findAll();
        System.out.println(userList1);

        // setter based
        userServiceWithDI = new UserServiceWithDI();
        userServiceWithDI.setUserRepository(userRepository);
        List<User> userList2 = userServiceWithDI.findAll();
        System.out.println(userList2);

        // interface based
        userServiceWithDI = new UserServiceWithDI();
        userServiceWithDI.configureUserRepository(userRepository);
        List<User> userList3 = userServiceWithDI.findAll();
        System.out.println(userList3);

    }

}
