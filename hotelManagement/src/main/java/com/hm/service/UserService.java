package com.hm.service;

import com.hm.dto.LoginDTO;
import com.hm.model.User;

import java.util.List;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
	public void deleteUser(User user);
	public User findUserById(Long id);
	public List<User> findAllUsers();
	public User checkLogin(LoginDTO login);
}
