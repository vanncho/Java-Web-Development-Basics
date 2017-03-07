package com.softuniGameStore.services;

import com.softuniGameStore.models.dtos.bindingModels.user.UserLoginModel;
import com.softuniGameStore.models.dtos.bindingModels.user.UserRegisterModel;

public interface UserService {

    void create(UserRegisterModel userRegisterModel);

    boolean exists(String username);

    UserLoginModel getByUsernameAndPassword(String username, String password);
}
