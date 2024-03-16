package com.app.securityservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.securityservice.config.JwtUtils;
import com.app.securityservice.entities.UserEntity;
import com.app.securityservice.model.UserModel;
import com.app.securityservice.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtils jwtUtils;

	@GetMapping("/")
	public ResponseEntity<String> getStarted(){
		return new ResponseEntity<String>("Up and Running",HttpStatus.OK);
	}
	
	@PostMapping("/addUser")
	public ResponseEntity<UserEntity> addNewUser(@RequestBody UserEntity user){
		UserEntity user1 = userService.addUser(user);
		return new ResponseEntity<UserEntity> (user1, HttpStatus.CREATED);
	}
	
	@GetMapping("/getUserDtls")
	public ResponseEntity<List<UserEntity>> getAllUsers(){
		 List<UserEntity> usersList = userService.getAllExiUsers();
		 return new ResponseEntity<List<UserEntity>> (usersList, HttpStatus.OK);
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<String> getAuthenticate(@RequestBody UserModel userModel){
		Authentication auth = new UsernamePasswordAuthenticationToken(userModel.getEmail(), userModel.getPassword());
		auth = authenticationManager.authenticate(auth);
		String token = jwtUtils.generateToken(auth);
		return new ResponseEntity<String> (token,HttpStatus.OK);
	}
}
