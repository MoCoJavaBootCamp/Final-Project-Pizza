package com.example.demo.ctrl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.UserService;
import com.example.demo.modal.User;

@RestController
@RequestMapping("/api")
public class UserCtrl {

  @Autowired
  private UserService userService;

  @GetMapping("/user")
  public List<User> get() {
    return userService.get();
  }

  @GetMapping("/user/{id}")
  public User get(@PathVariable long id) {
    return userService.get(id);
  }

  @PostMapping("/user")
  public User save(@RequestBody User user) {
    userService.save(user);
    return user;
  }

  @PutMapping("/user")
  public User update(@RequestBody User user) {
    userService.save(user);
    return user;
  }

  @DeleteMapping("/user/{id}")
  public String delete(@PathVariable long id) {
    userService.delete(id);
    return "User removed with id " + id;
  }
}