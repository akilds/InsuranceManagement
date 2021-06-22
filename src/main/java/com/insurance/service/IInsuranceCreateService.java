package com.insurance.service;

import java.util.List;

import com.insurance.dto.InsuranceCreateDTO;
import com.insurance.util.Response;

public interface IInsuranceCreateService {

	Response addInsurance(InsuranceCreateDTO insuranceDTO, String access);

	Response updateInsurance(String token, InsuranceCreateDTO insuranceDTO, boolean claimed, String access);

	List<?> getAllInsurance(String token, String access);

	Response deleteInsurance(String token, String access);
	
	List<?> getAllByStatus(String status, String token, String access);
	
	List<?> getAllByMonthPeriod(int monthPeriod, String token, String access);
	
	List<?> getAllInsuranceForUser(String fullName, String token, String access);

	List<?> getAllClaimedInsurance(String token, String access);
}
