package com.insurance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insurance.model.InsuranceCategoryData;

public interface InsuranceRepository extends JpaRepository<InsuranceCategoryData, Integer>{

	public Optional<InsuranceCategoryData> findByInsuranceCode(Long insuranceCode);
}
