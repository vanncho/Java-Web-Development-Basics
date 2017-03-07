package com.softuniGameStore.repositoriesImpl;

import com.softuniGameStore.models.entities.User;
import com.softuniGameStore.repositories.UserRepository;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@Local(UserRepository.class)
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(User user) {

        this.entityManager.persist(user);
    }

    @Override
    public boolean hasAdmin() {

        Query query = this.entityManager.createQuery("SELECT u FROM User AS u Where u.role = 'ADMIN'");
        User user = null;

        if (query.getResultList().size() > 0) {

            user = (User) query.getSingleResult();
            return true;
        }

        return false;
    }

    @Override
    public boolean exists(String email) {

        Query query = this.entityManager.createQuery("SELECT u FROM User AS u Where u.email = :email");

        query.setParameter("email", email);
        User user = null;

        if (query.getResultList().size() > 0) {

            user = (User) query.getSingleResult();
        }

        return user != null;
    }

    @Override
    public User getByEmailAndPassword(String email, String password) {

        Query  query = this.entityManager.createQuery("SELECT u FROM User AS u WHERE u.email = :email AND u.password = :password");
        query.setParameter("email", email);
        query.setParameter("password", password);

        User user = null;

        if (query.getResultList().size() > 0) {

            user = (User) query.getSingleResult();
        }

        return user;
    }
}
