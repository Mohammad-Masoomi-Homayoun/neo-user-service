package com.neo.neouserservice.user.service;

import com.neo.neouserservice.common.model.ID;
import com.neo.neouserservice.user.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {


    public static Map<ID, User> users = new HashMap<>();


    public User register(User user) {
        users.put(user.getId(), user);
        return users.get(user.getId());
    }

    public User login(User user) {

        return user;
    }

    public User getUser(String id) {

        User user = users.get(id);

        if (user == null)
            throw new RuntimeException("User not found");

        return user;
    }


}
