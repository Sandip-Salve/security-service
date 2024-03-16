package com.app.securityservice.model;

import java.util.HashSet;
import java.util.Set;

import com.app.securityservice.entities.Role;

public class UserModel {

	private String userName;
	private String email;
	private String password;
	private Set<Role> roles = new HashSet<Role>();
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "UserModel [userName=" + userName + ", email=" + email + ", password=" + password + ", roles=" + roles
				+ "]";
	}
	
	
	
}
