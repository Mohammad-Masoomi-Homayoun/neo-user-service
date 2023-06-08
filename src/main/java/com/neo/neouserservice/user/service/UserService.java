package com.neo.neouserservice.user.service;

import com.neo.neouserservice.common.model.ID;
import com.neo.neouserservice.user.model.User;
import com.neo.neouserservice.user.service.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User register(User user) {
        return userRepository.save(user);
    }

    public User login(User user) {


        return user;
    }

    public User getUser(ID id) {

        User user = userRepository.findById(id).get();

        if (user == null)
            throw new RuntimeException("User not found");

        return user;
    }


}
