package com.insurance.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.insurance.model.InsuranceUserData;
import com.insurance.model.UserRegistrationData;
import com.insurance.repository.UserRepository;

@Service
public class InsuranceUserDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		 Optional<UserRegistrationData> user = userRepository.findByMobileNo(userName);
	     user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));
	     return user.map(InsuranceUserData::new).get();
	}

}