package com.dao;

import com.videoondemand.model.User;

import java.util.List;

/**
 * Created by AndreaValenziano on 18/12/17.
 */
public interface UserDAO {
    void insert(User user);
    void update(User user);
    void delete(User user);
    User findById(int id);
    List<User> findAll();
}
