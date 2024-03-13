package hu.nye.spring.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hu.nye.spring.core.entity.UserEntity;
import hu.nye.spring.core.request.UserRequest;
import hu.nye.spring.core.service.IUserService;

@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/users")
    public UserEntity saveUser(@RequestBody UserRequest request) {
        return userService.saveUser(request);
    }

    @GetMapping("/users/{id}")
    public UserEntity getUser(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }
}
