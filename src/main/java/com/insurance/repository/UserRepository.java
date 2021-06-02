package com.insurance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insurance.model.UserData;

public interface UserRepository extends JpaRepository<UserData, Integer>{

	public Optional<UserData> findByMobileNo(String mobileNo);
}
