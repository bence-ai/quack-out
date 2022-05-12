package com.codecool.shop.dao;

import com.codecool.shop.service.User;

import java.util.List;

public interface UserDao {
    void add(User user);

    void update(User user);

    User get(int id);

    String getHashedPasswordByName (String username);

    List<User> getAll();
}
