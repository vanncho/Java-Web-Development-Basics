package softuni.servicesImpl;

import softuni.entities.User;
import softuni.mapper.ModelParser;
import softuni.models.bindingModels.UserModel;
import softuni.repositories.UserRepository;
import softuni.services.UserService;

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

    public UserServiceImpl() {
    }

    @Override
    public UserModel getById(Long id) {

        User user = this.userRepository.getById(id);
        UserModel userModel = modelParser.convert(user, UserModel.class);

        return userModel;
    }

    @Override
    public UserModel getByEmail(String email) {

        User user = this.userRepository.getByEmail(email);
        UserModel userModel = modelParser.convert(user, UserModel.class);
        return userModel;
    }

    @Override
    public UserModel getByEmailAndPassword(String email, String password) {

        User user = this.userRepository.getByEmailAndPassword(email, password);
        UserModel userModel = modelParser.convert(user, UserModel.class);
        return userModel;
    }

    @Override
    public void create(UserModel user) {

        User userToPersist = this.modelParser.convert(user, User.class);
        this.userRepository.create(userToPersist);
    }

    @Override
    public boolean exists(String email) {

        return this.userRepository.exists(email);
    }
}
