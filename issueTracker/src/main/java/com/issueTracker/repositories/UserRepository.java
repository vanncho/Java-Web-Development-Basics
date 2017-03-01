package com.issueTracker.repositories;

import com.issueTracker.entities.User;

public interface UserRepository {

    User getById(Long id);

    User getByUsername(String username);

    User getByUsernameAndPassword(String username, String password);

    void create(User user);

    boolean exists(String username);

    boolean hasAdmin();
}
