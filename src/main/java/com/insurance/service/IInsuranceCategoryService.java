package com.insurance.service;

import java.util.List;

import com.insurance.dto.InsuranceDTO;
import com.insurance.model.InsuranceCategoryData;
import com.insurance.util.Response;

public interface IInsuranceCategoryService {

	Response addInsurance(InsuranceDTO insuranceDTO);

	Response updateInsurance(String token, InsuranceDTO insuranceDTO);

	List<InsuranceCategoryData> getAllInsurance(String token);

	Response deleteInsurance(String token);
}
