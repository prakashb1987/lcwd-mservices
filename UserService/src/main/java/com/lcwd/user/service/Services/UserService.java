package com.lcwd.user.service.Services;

import com.lcwd.user.service.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    List<User> getAllUsers();

    User getUser(String userId);
}
