package com.hm.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import com.hm.dto.LoginDTO;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hm.model.Role;
import com.hm.model.User;
import com.hm.repository.RoleRepository;
import com.hm.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private Logger log;

	@Autowired
	private UserRepository userRepository;
	@Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void saveUser(User user) {
        user.setActive(1);
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
		log.info("USER HAS BEEN CREATED");
	}

	@Override
	public void deleteUser(User user) {
		userRepository.delete(user);
		log.info("USER HAS BEEN DELETED");
	}

	@Override
	public User findUserById(Long id) {
		log.info("FIND USER BY ID");
		return userRepository.findOne(id);
	}

	@Override
	public List<User> findAllUsers() {
		log.info("FIND ALL USERS");
		List<User> users = userRepository.findAll();
		for (User user: users) {
			System.out.println(user);
		}
		return users;
	}

	@Override
	public User checkLogin(LoginDTO login) {
		//String pass = bCryptPasswordEncoder.encode(login.getPassword());
		User user = userRepository.findByEmail(login.getEmail());
		if(user == null) return null;
		System.out.println(user);
		if(user.getPassword().equals(login.getPassword())) {
			return user;
		}

		return null;
	}

}
