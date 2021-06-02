package com.insurance.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.ToString;

@Data
public @ToString class InsuranceCreateDTO {
	
	@NotNull(message = "Month Period cannot be Empty")
	public int monthPeriod;
	
	@NotEmpty(message = "Status cannot be Empty")
	public String status;
}
