package com.wallet.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wallet.entities.User;
import com.wallet.repository.UserRepository;
import com.wallet.service.UserService;

@Service
public class UserServiceImpl implements UserService	{
	
	@Autowired
	UserRepository repository;
	
	public User save(User u) {
		return repository.save(u);
	}
	
	public Optional<User> findByEmail(String email) {
		return repository.findByEmailEquals(email);
	}
}
