package com.insurance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.model.InsuranceCategoryData;

@Repository
public interface InsuranceRepository extends JpaRepository<InsuranceCategoryData, Integer>{

	public Optional<InsuranceCategoryData> findByInsuranceCode(Long insuranceCode);
}
