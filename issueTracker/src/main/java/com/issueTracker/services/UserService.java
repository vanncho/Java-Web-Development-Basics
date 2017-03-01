package com.issueTracker.services;

import com.issueTracker.models.UserModel;

public interface UserService {

    UserModel getById(Long id);

    UserModel getByUsername(String username);

    UserModel getByUsernameAndPassword(String username, String password);

    void create(UserModel user);

    boolean exists(String username);

    boolean hasAdmin();
}
