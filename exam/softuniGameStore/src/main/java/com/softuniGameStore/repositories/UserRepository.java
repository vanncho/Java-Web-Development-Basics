package com.softuniGameStore.repositories;

import com.softuniGameStore.models.entities.User;

public interface UserRepository {

    void create(User user);

    boolean hasAdmin();

    boolean exists(String email);

    User getByEmailAndPassword(String email, String password);

}
