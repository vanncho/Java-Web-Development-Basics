package com.issueTracker.servicesImpl;

import com.issueTracker.entities.User;
import com.issueTracker.mapper.ModelParser;
import com.issueTracker.models.UserModel;
import com.issueTracker.repositories.UserRepository;
import com.issueTracker.services.UserService;

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
    public UserModel getById(Long id) {

        User user = this.userRepository.getById(id);
        UserModel userModel = null;

        if (user != null) {

            userModel = this.modelParser.convert(user, UserModel.class);
        }

        return userModel;
    }

    @Override
    public UserModel getByUsername(String username) {

        User user = this.userRepository.getByUsername(username);
        UserModel userModel = null;

        if (user != null) {

            userModel = this.modelParser.convert(user, UserModel.class);
        }

        return userModel;
    }

    @Override
    public UserModel getByUsernameAndPassword(String username, String password) {

        User user = this.userRepository.getByUsernameAndPassword(username, password);
        UserModel userModel = null;

        if (user != null) {

            userModel = this.modelParser.convert(user, UserModel.class);
        }

        return userModel;
    }

    @Override
    public void create(UserModel userModel) {

        User user = this.modelParser.convert(userModel, User.class);
        this.userRepository.create(user);
    }

    @Override
    public boolean exists(String username) {

        return this.userRepository.exists(username);
    }

    @Override
    public boolean hasAdmin() {

        return this.userRepository.hasAdmin();
    }
}
