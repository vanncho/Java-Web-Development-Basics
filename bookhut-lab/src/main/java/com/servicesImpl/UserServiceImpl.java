package com.servicesImpl;

import com.entities.User;
import com.mapper.ModelParser;
import com.mapper.ModelParserImpl;
import com.models.bindingModels.LoginModel;
import com.repository.UserRepository;
import com.repositoryImpl.UserRepositoryImpl;
import com.services.UserService;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelParser modelParser;

    public UserServiceImpl() {

        this.userRepository = UserRepositoryImpl.getInstance();
        this.modelParser = new ModelParserImpl();
    }

    @Override
    public void createUser(LoginModel loginModel) {

        User user = this.modelParser.convert(loginModel, User.class);
        this.userRepository.createUser(user);

    }

    @Override
    public LoginModel findByUsernameAndPassword(String username, String password) {

        User user = this.userRepository.findByUsernameAndPassword(username, password);
        LoginModel loginModel = null;

        if (user != null) {

            loginModel = this.modelParser.convert(user, LoginModel.class);
        }

        return loginModel;
    }
}
