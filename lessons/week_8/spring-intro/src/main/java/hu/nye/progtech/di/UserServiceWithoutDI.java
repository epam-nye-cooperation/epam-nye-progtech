package hu.nye.progtech.di;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceWithoutDI {

    private UserRepository userRepository;

    public UserServiceWithoutDI() {
        Map<Integer, User> userMap = new HashMap<>();
        userRepository = new UserRepository(userMap);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

}
