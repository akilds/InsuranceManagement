package com.insurance.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	private int monthPeriod;
	private String status;
	private LocalDateTime createRegisteredDate = LocalDateTime.now();
	private LocalDateTime createUpdatedDate = LocalDateTime.now();
	private boolean claimed = false;

	@ManyToOne
	@JoinColumn(name = "user_cid", referencedColumnName = "user_id")
	private UserRegistrationData userCid;
	
	@ManyToOne
	@JoinColumn(name = "insurance_cid", referencedColumnName = "insurance_id")
	private InsuranceCategoryData insuranceCid;
	
	public InsuranceCreateData() {}

	public InsuranceCreateData(int id, UserRegistrationData userId, InsuranceCategoryData insuranceId, int monthPeriod, String status,
			LocalDateTime createRegisteredDate, LocalDateTime createUpdatedDate) {
		super();
		this.id = id;
		this.userCid = userId;
		this.insuranceCid = insuranceId;
		this.monthPeriod = monthPeriod;
		this.status = status;
		this.createRegisteredDate = createRegisteredDate;
		this.createUpdatedDate = createUpdatedDate;
	}
	
	public void updateInsurance(InsuranceCreateDTO insuranceDTO, boolean claimed) {
		this.userCid = insuranceDTO.userCid;
		this.insuranceCid = insuranceDTO.insuranceCid;
		this.monthPeriod = insuranceDTO.monthPeriod;
		this.status = insuranceDTO.status;
		this.createUpdatedDate = LocalDateTime.now();
		this.claimed = claimed;
	}

}
