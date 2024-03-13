package hu.nye.spring.core.repistory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import hu.nye.spring.core.model.User;

@Repository
public class UserRepository {

    private final User user;

    @Autowired
    public UserRepository(@Qualifier("createLoggedUser") User user) {
        this.user = user;
    }

    public User getLoggedUser(){
        return user;
    }

    public String getLoggedUserName() {
        return user.getName();
    }
}
