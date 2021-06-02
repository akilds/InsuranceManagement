package com.insurance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.insurance.model.InsuranceCreateData;

public interface InsuranceCreateRepository extends JpaRepository<InsuranceCreateData, Integer>{

	@Query(value = "select * from user_data, insurance_data, insurance_create where insurance_create.status = :status", nativeQuery = true)
	List<?> findAllByStatus(String status);
	
	@Query(value = "select * from user_data, insurance_data, insurance_create where insurance_create.month_period = :monthPeriod", nativeQuery = true)
	List<?> findAllByMonthPeriod(int monthPeriod);
	
	@Query(value = "select insurance_data.insurance_name from user_data, insurance_data, insurance_create where user_data.full_name = :fullName", nativeQuery = true)
	List<?> findAllInsuranceForUser(String fullName);
}
