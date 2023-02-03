package com.example.PP31._spring_boot.dao;

import com.example.PP31._spring_boot.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = entityManager.createQuery("FROM User", User.class).getResultList();
        return users;
    }

    @Override
    public User getOneUserById(long id) {
        TypedQuery<User> query = entityManager.createQuery("select user from User user where user.id =:id", User.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void editUser(long id, User user) {
        User newUser = getOneUserById(id);
        newUser.setName(user.getName());
        newUser.setSecondName(user.getSecondName());
        newUser.setEmail(user.getEmail());
        entityManager.merge(newUser);
    }

    @Override
    public void deleteUser(long id) {
        User user = getOneUserById(id);
        entityManager.remove(user);
    }
}

