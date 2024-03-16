package com.app.securityservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.securityservice.dao.UserEntityRepository;
import com.app.securityservice.entities.UserEntity;
import com.app.securityservice.model.SecuredUser;

@Service
@Transactional
public class UserService implements UserDetailsService{

	@Autowired
	private UserEntityRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity exiUser = userRepo.findByEmail(username);
		if(exiUser==null) {
			throw new UsernameNotFoundException(username+" not found");
		}
		return new SecuredUser(exiUser);
	}
	
	public UserEntity addUser(UserEntity user) {
		UserEntity savedUser = null;
		if(user!=null) {
			savedUser = userRepo.save(user);
		}
		return savedUser;
	}
	
	public List<UserEntity> getAllExiUsers(){
		return userRepo.findAll();
	}
	
	
}
