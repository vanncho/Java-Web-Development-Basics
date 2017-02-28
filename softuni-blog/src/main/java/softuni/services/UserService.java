package softuni.services;

import softuni.models.bindingModels.UserModel;

public interface UserService {

    UserModel getById(Long id);

    UserModel getByEmail(String email);

    UserModel getByEmailAndPassword(String email, String password);

    void create(UserModel user);

    boolean exists(String email);
}
