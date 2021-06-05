package com.insurance.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.insurance.model.UserData;

@Repository
public interface UserRepository extends JpaRepository<UserData, Integer>{

	public Optional<UserData> findByMobileNo(String mobileNo);
	
	public List<UserData> findAllByHealthCondition(String healthCondition);
	
	public List<UserData> findAllByVehicleData(String vehicleData);
	
	@Query(value = "select * from user_data where registered_date between ?1 and ?2", nativeQuery = true)
	List<UserData> findAllUserBetweenDates(LocalDateTime date1, LocalDateTime date2);
}
