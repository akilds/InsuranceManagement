package com.insurance.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.insurance.model.UserRegistrationData;

@Repository
public interface UserRepository extends JpaRepository<UserRegistrationData, Integer>{

	public Optional<UserRegistrationData> findByMobileNo(String mobileNo);
	
	public List<UserRegistrationData> findAllByHealthCondition(String healthCondition);
	
	public List<UserRegistrationData> findAllByVehicleData(String vehicleData);
	
	@Query(value = "select * from user_data where registered_date between ?1 and ?2", nativeQuery = true)
	List<UserRegistrationData> findAllUserBetweenDates(LocalDateTime date1, LocalDateTime date2);
}
