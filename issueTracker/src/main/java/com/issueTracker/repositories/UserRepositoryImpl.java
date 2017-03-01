package com.issueTracker.repositories;

import com.issueTracker.entities.User;

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
    public User getById(Long id) {

        //return this.entityManager.find(User.class, id);
        Query query = this.entityManager.createQuery("SELECT u FROM User AS u WHERE u.id = :id");
        query.setParameter("id", id);
        User user = null;

        if (query.getResultList().size() > 0) {

            user = (User) query.getSingleResult();
        }

        return user;
    }

    @Override
    public User getByUsername(String username) {

        Query query = this.entityManager.createQuery("SELECT u FROM User AS u WHERE u.username = :username");
        query.setParameter("username", username);
        User user = null;

        if (query.getResultList().size() > 0) {

            user = (User) query.getSingleResult();
        }

        return user;
    }

    @Override
    public User getByUsernameAndPassword(String username, String password) {
        Query query = this.entityManager.createQuery("SELECT u FROM User AS u WHERE u.username = :username AND u.password = :password");
        query.setParameter("username", username);
        query.setParameter("password", password);

        User user = null;

        if (query.getResultList().size() > 0) {

            user = (User) query.getSingleResult();
        }

        return user;
    }

    @Override
    public void create(User user) {

        this.entityManager.persist(user);
    }

    @Override
    public boolean exists(String username) {

        Query query = this.entityManager.createQuery("SELECT u FROM User AS u Where u.username = :username");
        query.setParameter("username", username);
        User user = null;

        if (query.getResultList().size() > 0) {

            user = (User) query.getSingleResult();
        }

        return user != null;
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
}
