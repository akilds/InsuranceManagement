package com.insurance.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class InsuranceUserData implements UserDetails{

	private static final long serialVersionUID = 1L;
	private String userName;
	private List<GrantedAuthority> authorities;
	private String password;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public InsuranceUserData(UserRegistrationData user) {
		this.userName = String.valueOf(user.getMobileNo());
		this.password = user.getPassword();
		this.authorities = Arrays.stream(user.getUserAccess().split(","))
                					.map(SimpleGrantedAuthority::new)
                					.collect(Collectors.toList());
	}

	public InsuranceUserData() {
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public String getPassword() {
		return password;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
}
