package com.insurance.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.ToString;

@Data
public @ToString class InsuranceDTO {

	@NotEmpty(message = "Insurance Name cannot be Empty")
	public String insuranceName;
	
	@NotEmpty(message = "Insurance Status cannot be Empty")
	public String insuranceStatus;
	
	@NotEmpty(message = "Insurance Scheme cannot be Empty")
	public String insuranceScheme;
	
	@NotNull(message = "Insurance Code cannot be Empty")
	public Long insuranceCode;
}
