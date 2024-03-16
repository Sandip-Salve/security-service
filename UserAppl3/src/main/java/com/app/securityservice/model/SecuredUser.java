package com.app.securityservice.model;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.app.securityservice.entities.UserEntity;

public class SecuredUser implements UserDetails{
	public static final long serialVersionUID = 10L;
	
	private String userName;
	private String password;
	private boolean enabled;
	private List<GrantedAuthority> authorities;
	
	public SecuredUser(UserEntity user) {
		this.userName = user.getEmail();
		this.password = user.getPassword();
		this.enabled = true;
		this.authorities = getAuthoritiesList(user);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public List<GrantedAuthority> getAuthoritiesList(UserEntity user){
		List<GrantedAuthority> list = user.getRoles().stream().map(elt->new SimpleGrantedAuthority(elt.getRoleName())).collect(Collectors.toList());
		return list;
	}
	
}
