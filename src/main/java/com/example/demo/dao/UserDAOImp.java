package com.example.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.modal.User;

@Repository
public class UserDAOImp implements UserDAO {

  @Autowired
  private EntityManager entityManager;

  @Override
  public List<User> get() {

    Session currSession = entityManager.unwrap(Session.class);
    Query<User> query = currSession.createQuery("from User", User.class);
    List<User> set = query.getResultList();
    return set;

  }

  @Override
  public User get(long id) {

    Session currSession = entityManager.unwrap(Session.class);
    User user = currSession.get(User.class, id);
    return user;

  }

  @Override
  public void save(User user) {

    entityManager = entityManager.getEntityManagerFactory().createEntityManager();
    Session currSession = entityManager.unwrap(Session.class);
    currSession.saveOrUpdate(user);

  }

  @Override
  public void delete(long id) {
    Session currSession = entityManager.unwrap(Session.class);
    User user = currSession.get(User.class, id);
    currSession.delete(user);
  }
}