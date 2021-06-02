package com.insurance.dto;

import java.io.File;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.ToString;

@Data
public @ToString class UserDTO {

	@NotEmpty(message = "Name cannot be Empty")
	public String fullName;
	
	@NotEmpty(message = "Address cannot be Empty")
	public String permenantAddress;
	
	@NotEmpty(message = "Address cannot be Empty")
	public String tempAddress;
	
	@NotEmpty(message = "Mobile Number cannot be Empty")
	public String mobileNo;
	
	@NotNull(message = "Age cannot be Empty")
	public int age;
	
	@NotEmpty(message = "Occupation cannot be Empty")
	public String occupation;
	
	@NotEmpty(message = "Family Background cannot be Empty")
	public String familyBackground;
	
	public File KYC;
	
	@NotEmpty(message = "Health Condition cannot be Empty")
	public String healthCondition;
	
	@NotEmpty(message = "Vehicle Data cannot be Empty")
	public String vehicleData;
}
