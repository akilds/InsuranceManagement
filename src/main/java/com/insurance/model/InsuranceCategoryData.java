package com.insurance.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.insurance.dto.InsuranceDTO;

import lombok.Data;

@Entity
@Table(name = "insurance_data")
public @Data class InsuranceCategoryData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "insurance_id")
	private int insuranceId;
	
	@Column
	private String insuranceName;
	private String insuranceStatus;
	private String insuranceScheme;
	private Long insuranceCode;
	private LocalDateTime insuranceRegisteredDate = LocalDateTime.now();
	private LocalDateTime insuranceUpdatedDate = LocalDateTime.now();
	
	public InsuranceCategoryData() {}
	
	public InsuranceCategoryData(int id) {
		this.insuranceId = id;
	}
	
	public InsuranceCategoryData(int id, String insuranceName, String insuranceStatus, String insuranceScheme,
			Long insuranceCode, LocalDateTime registeredDate, LocalDateTime updatedDate) {
		super();
		this.insuranceId = id;
		this.insuranceName = insuranceName;
		this.insuranceStatus = insuranceStatus;
		this.insuranceScheme = insuranceScheme;
		this.insuranceCode = insuranceCode;
		this.insuranceRegisteredDate = registeredDate;
		this.insuranceUpdatedDate = updatedDate;
	}
	
	public void updateInsurance(InsuranceDTO insuranceDTO) {
		this.insuranceName = insuranceDTO.insuranceName;
		this.insuranceStatus = insuranceDTO.insuranceStatus;
		this.insuranceScheme = insuranceDTO.insuranceScheme;
		this.insuranceCode = insuranceDTO.insuranceCode;
		this.insuranceUpdatedDate = LocalDateTime.now();
	}
}
