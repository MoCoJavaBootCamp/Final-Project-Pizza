package com.example.demo.dao;

import java.util.List;

import com.example.demo.modal.User;

public interface UserDAO {
    List<User> get();
    User get(long id);
    public void save (User user);
    public void delete(long id);
}
