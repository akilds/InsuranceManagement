package com.insurance.service;

import java.util.List;

import com.insurance.dto.InsuranceCreateDTO;
import com.insurance.model.InsuranceCreateData;
import com.insurance.util.Response;

public interface IInsuranceCreateService {

	Response addInsurance(String userToken, String insuranceToken, InsuranceCreateDTO insuranceDTO);

	Response updateInsurance(String token, InsuranceCreateDTO insuranceDTO);

	List<InsuranceCreateData> getAllInsurance(String token);

	Response deleteInsurance(String token);
	
	List<?> getAllByStatus(String status, String token);
	
	List<?> getAllByMonthPeriod(int monthPeriod, String token);
	
	List<?> getAllInsuranceForUser(String fullName, String token);
}
