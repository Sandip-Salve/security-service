package com.app.securityservice.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "USER_ENTITY")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Long userId;
	
	@Column(name = "USER_NAME", length = 30)
	private String userName;
	
	@Column(name = "USER_EMAIL", length = 25, unique = true)
	private String email;
	
	@Column(name = "USER_PASSWRD", length = 150)
	private String password;
	
	@Column(name = "ENTRY_DT")
	private Date creationDate = new Date();
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE})
	@JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name="ROLE_ID"))
	private Set<Role> roles = new HashSet<Role>();

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

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

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "UserEntity [userId=" + userId + ", userName=" + userName + ", email=" + email + ", password=" + password
				+ ", creationDate=" + creationDate + ", roles=" + roles + "]";
	}
	
	
}
