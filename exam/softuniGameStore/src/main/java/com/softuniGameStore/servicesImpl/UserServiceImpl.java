package com.softuniGameStore.servicesImpl;


import com.softuniGameStore.mapper.ModelParser;
import com.softuniGameStore.models.dtos.bindingModels.user.UserLoginModel;
import com.softuniGameStore.models.dtos.bindingModels.user.UserRegisterModel;
import com.softuniGameStore.models.entities.User;
import com.softuniGameStore.models.entities.enumerations.Role;
import com.softuniGameStore.repositories.UserRepository;
import com.softuniGameStore.services.UserService;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
@Local(UserService.class)
public class UserServiceImpl implements UserService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private ModelParser modelParser;

    @Override
    public void create(UserRegisterModel userRegisterModel) {

        Role role = this.getUserGetRole();
        User user = this.modelParser.convert(userRegisterModel, User.class);
        user.setRole(role);

        this.userRepository.create(user);
    }

    @Override
    public boolean exists(String email) {

        return this.userRepository.exists(email);
    }

    @Override
    public UserLoginModel getByUsernameAndPassword(String username, String password) {

        User user = this.userRepository.getByEmailAndPassword(username, password);
        UserLoginModel userLoginModel = null;

        if (user != null) {

            userLoginModel = this.modelParser.convert(user, UserLoginModel.class);
            Role role = user.getRole();
            userLoginModel.setRole(role);
        }

        return userLoginModel;
    }

    private Role getUserGetRole() {

        boolean hasAdmin = this.hasAdmin();
        Role role = Role.USER;

        if (!hasAdmin) {

            role = Role.ADMIN;
            return role;
        }

        return role;
    }

    private boolean hasAdmin() {

        return this.userRepository.hasAdmin();
    }
}
