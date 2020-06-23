package com.example.demo.service;

import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.UserDAO;
import com.example.demo.modal.User;

@Service
public class UserServiceImp implements UserService {

  @Autowired
  private UserDAO userDao;

  @Transactional
  @Override
  public List<User> get() {
    return userDao.get();
  }

  @Transactional
  @Override
  public User get(long id) {
    return userDao.get(id);
  }

  @Transactional
  @Override
  public void save(User user) {
   userDao.save(user);
  }

  @Transactional
  @Override
  public void delete(long id) {
    userDao.delete(id);
  }
}