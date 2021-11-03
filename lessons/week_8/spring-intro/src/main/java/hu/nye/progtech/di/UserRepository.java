package hu.nye.progtech.di;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class UserRepository {

    private Map<Integer, User> userMap;

    public UserRepository(Map<Integer, User> userMap) {
        this.userMap = userMap;
    }

    public List<User> findAll() {
        return Collections.emptyList();
    }

}
