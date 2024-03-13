package hu.nye.spring.core.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import hu.nye.spring.core.model.User;
import lombok.extern.slf4j.Slf4j;

@Configuration
@PropertySource(value = "application.properties")
@Slf4j
public class UserServiceConfiguration {

    @Value("${user.nickname}")
    private String name;

    @Value("${user.age}")
    private int age;

    @Bean
    public User createLoggedUser(){
        log.info("Logged user: {} {}", name, age);
        return new User(name, age);
    }
}
