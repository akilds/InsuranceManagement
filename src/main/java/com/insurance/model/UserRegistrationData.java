package com.insurance.model;

import java.io.File;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.insurance.dto.UserDTO;

import lombok.Data;

@Entity
@Table(name = "user_data")
public @Data class UserRegistrationData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int userId;
	
	@Column
	private String fullName;
	private String mobileNo;
	private int age;
	private String emailId;
	private String password;
	private String occupation;
	private String familyBackground;
	private File KYC;
	private String healthCondition;
	private String vehicleData;
	private LocalDateTime userRegisteredDate = LocalDateTime.now();
	private LocalDateTime userUpdatedDate = LocalDateTime.now();
	private String permenantAddress;
	private String tempAddress;
	private String userAccess;
	
	public UserRegistrationData() {}

	public UserRegistrationData(int id) {
		super();
		this.userId = id;
	}

	public void updateUser(UserDTO userDTO) {
		this.fullName = userDTO.fullName;
		this.mobileNo = userDTO.mobileNo;
		this.age = userDTO.age;
		this.occupation = userDTO.occupation;
		this.familyBackground = userDTO.familyBackground;
		this.KYC = userDTO.KYC;
		this.healthCondition = userDTO.healthCondition;
		this.vehicleData = userDTO.vehicleData;
		this.userUpdatedDate = LocalDateTime.now();
		this.tempAddress = userDTO.tempAddress;
		this.permenantAddress = userDTO.permenantAddress;
		this.userAccess = userDTO.userAccess;
	}
}
