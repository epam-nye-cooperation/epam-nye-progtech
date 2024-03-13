package hu.nye.spring.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.nye.spring.core.entity.UserEntity;
import hu.nye.spring.core.repistory.IUserRepository;
import hu.nye.spring.core.request.UserRequest;

@Service
public class UserService implements IUserService{

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserEntity saveUser(UserRequest userRequest) {
        UserEntity userEntity = UserEntity.builder()
            .name(userRequest.getName())
            .age(userRequest.getAge())
            .email(userRequest.getEmail())
            .build();
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public UserEntity updateUser(Long id, UserRequest userRequest) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow();
        userEntity.setName(userRequest.getName());
        userEntity.setEmail(userRequest.getEmail());
        userEntity.setAge(userRequest.getAge());
        return userRepository.save(userEntity);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    /*public void printLoggedUser(){
        System.out.println(userRepository.getLoggedUser());
    }

    public User getUser() {
        return userRepository.getLoggedUser();
    }*/
}
