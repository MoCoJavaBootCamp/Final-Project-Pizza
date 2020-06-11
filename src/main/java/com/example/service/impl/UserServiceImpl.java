package com.example.service.impl;

import com.example.demo.repository.UserRepository;
import com.example.demo.tables.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User searchUser(final String email) {
		return userRepository.findByUsername(email);
	}
}
