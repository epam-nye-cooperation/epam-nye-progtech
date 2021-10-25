package hu.nye.progtech.di;

import java.util.List;

public class UserServiceWithoutDI {

    private UserRepository userRepository;

    public UserServiceWithoutDI() {
        userRepository = new UserRepository();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

}
