package com.insurance.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.insurance.model.InsuranceCategoryData;
import com.insurance.model.UserRegistrationData;

import lombok.Data;
import lombok.ToString;

@Data
public @ToString class InsuranceCreateDTO {
	
	public UserRegistrationData userCid;
	
	public InsuranceCategoryData insuranceCid;
	
	@NotNull(message = "Month Period cannot be Empty")
	public int monthPeriod;
	
	@NotEmpty(message = "Status cannot be Empty")
	public String status;
	
}
