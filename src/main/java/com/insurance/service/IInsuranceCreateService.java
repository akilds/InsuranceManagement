package com.insurance.service;

import java.util.List;

import com.insurance.dto.InsuranceCreateDTO;
import com.insurance.util.Response;

public interface IInsuranceCreateService {

	Response addInsurance(InsuranceCreateDTO insuranceDTO);

	Response updateInsurance(String token, InsuranceCreateDTO insuranceDTO, boolean claimed);

	List<?> getAllInsurance(String token);

	Response deleteInsurance(String token);
	
	List<?> getAllByStatus(String status, String token);
	
	List<?> getAllByMonthPeriod(int monthPeriod, String token);
	
	List<?> getAllInsuranceForUser(String fullName, String token);

	List<?> getAllClaimedInsurance(String token);
}
