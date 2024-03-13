package hu.nye.spring.core.service;

import hu.nye.spring.core.entity.UserEntity;
import hu.nye.spring.core.request.UserRequest;

public interface IUserService {

    UserEntity saveUser(UserRequest userRequest);

    UserEntity getUserById(Long id);

    UserEntity updateUser(Long id, UserRequest userRequest);

    void deleteUserById(Long id);
}
