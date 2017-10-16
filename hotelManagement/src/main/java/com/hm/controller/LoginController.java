package com.hm.controller;

import javax.validation.Valid;

import com.hm.dto.LoginDTO;
import com.hm.dto.Response;
import com.hm.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hm.model.User;
import com.hm.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="/api/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	Response<UserDTO> login(@Valid @RequestBody LoginDTO login) {
		System.out.println(login);
		User user = userService.checkLogin(login);
		if (user == null) {
			return Response.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name());
		} else {
			UserDTO userDTO = new UserDTO();
			userDTO.setEmail(user.getEmail());
			userDTO.setId(user.getId());
			userDTO.setName(user.getName());
			userDTO.setLastName(user.getLastName());
			userDTO.setRole(user.getRoles());
			System.out.println(user);
			return Response.ok(userDTO, HttpStatus.OK.value(), HttpStatus.OK.name());
		}
	}
	
	@RequestMapping(value = "/api/registration", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	Response<User> createNewUser(@Valid @RequestBody User user, BindingResult bindingResult){
		System.out.println(user);
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult
					.rejectValue("email", "error.user",
							"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			return Response.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name());
		} else {
			userService.saveUser(user);
			return Response.ok(user, HttpStatus.OK.value(), HttpStatus.OK.name());
		}
	}

}
