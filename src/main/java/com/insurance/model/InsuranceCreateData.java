package com.insurance.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.insurance.dto.InsuranceCreateDTO;

import lombok.Data;

@Entity
@Table(name = "insurance_create")
public @Data class InsuranceCreateData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column
	private int userCid;
	private int insuranceCid;
	private int monthPeriod;
	private String status;
	private LocalDateTime createRegisteredDate = LocalDateTime.now();
	private LocalDateTime createUpdatedDate = LocalDateTime.now();

	public InsuranceCreateData() {}
	
	public InsuranceCreateData(int userId, int insuranceId, InsuranceCreateDTO insuranceDTO) {
		this.userCid = userId;
		this.insuranceCid = insuranceId;
		this.monthPeriod = insuranceDTO.monthPeriod;
		this.status = insuranceDTO.status;
		this.createRegisteredDate = LocalDateTime.now();
		this.createUpdatedDate = LocalDateTime.now();
	}
	
	public void updateInsurance(InsuranceCreateDTO insuranceDTO) {
		this.monthPeriod = insuranceDTO.monthPeriod;
		this.status = insuranceDTO.status;
		this.createUpdatedDate = LocalDateTime.now();
	}
}
