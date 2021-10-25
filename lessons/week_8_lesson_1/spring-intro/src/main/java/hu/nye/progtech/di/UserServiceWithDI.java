package hu.nye.progtech.di;

import java.util.List;

public class UserServiceWithDI implements UserRepositorySetter {

    private UserRepository userRepository;

    // setter injection
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserServiceWithDI() {
    }

    // constructor injection
    public UserServiceWithDI(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    // interface injection
    @Override
    public void configureUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
