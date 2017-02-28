package softuni.repositories;

import softuni.entities.User;

public interface UserRepository {

    User getById(Long id);

    User getByEmail(String email);

    User getByEmailAndPassword(String email, String password);

    void create(User user);

    boolean exists(String email);
}
