package com.example.demo.service;

import java.util.List;

import com.example.demo.modal.User;

public interface UserService {
  List<User> get();
  User get(long id);
  public void save(User user);
  public void delete(long id);
}