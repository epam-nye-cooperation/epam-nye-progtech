package hu.nye.spring.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hu.nye.spring.core.configuration.UserServiceConfiguration;
import hu.nye.spring.core.service.UserService;

@SpringBootApplication
public class Main {


    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        /*ApplicationContext appContext = new AnnotationConfigApplicationContext("hu.nye.spring.core");
        UserServiceConfiguration userServiceConfiguration = appContext.getBean(UserServiceConfiguration.class);
        UserService userService = appContext.getBean(UserService.class);
        userService.printLoggedUser();*/
    }
}
