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
public @Data class UserData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int userId;
	
	@Column
	private String fullName;
	private String mobileNo;
	private int age;
	private String occupation;
	private String familyBackground;
	private File KYC;
	private String healthCondition;
	private String vehicleData;
	private LocalDateTime userRegisteredDate = LocalDateTime.now();
	private LocalDateTime userUpdatedDate = LocalDateTime.now();
	private String permenantAddress;
	private String tempAddress;
	
	public UserData() {}

	public UserData(int id) {
		super();
		this.userId = id;
	}
	public UserData(int id, String fullName, String mobileNo, int age, String occupation, String familyBackground,
			File kYC, String healthCondition, String vehicleData, LocalDateTime registeredDate,
			LocalDateTime updatedDate, String tempAddress, String permenantAddress) {
		super();
		this.userId = id;
		this.fullName = fullName;
		this.mobileNo = mobileNo;
		this.age = age;
		this.occupation = occupation;
		this.familyBackground = familyBackground;
		this.KYC = kYC;
		this.healthCondition = healthCondition;
		this.vehicleData = vehicleData;
		this.userRegisteredDate = registeredDate;
		this.userUpdatedDate = updatedDate;
		this.tempAddress = tempAddress;
		this.permenantAddress = permenantAddress;
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
	}
}
