package com.insurance.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.insurance.model.InsuranceCategoryData;

@Repository
public interface InsuranceRepository extends JpaRepository<InsuranceCategoryData, Integer>{

	public Optional<InsuranceCategoryData> findByInsuranceCode(Long insuranceCode);
	
	public List<InsuranceCategoryData> findAllByInsuranceId(int id);
	
	@Query(value = "select * from insurance_data where registered_date between ?1 and ?2", nativeQuery = true)
	List<InsuranceCategoryData> findAllInsuranceBetweenDates(LocalDateTime date1, LocalDateTime date2);
}
