package com.example.PP31._spring_boot.dao;

import com.example.PP31._spring_boot.model.User;

import java.util.List;

public interface UserDAO {

    List<User> getUsers();
    User getOneUserById(long id);
    void addUser(User user);
    void editUser(long id, User user);
    void deleteUser(long id);
}
