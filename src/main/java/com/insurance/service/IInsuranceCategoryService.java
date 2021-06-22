package com.insurance.service;

import java.util.List;

import com.insurance.dto.InsuranceDTO;
import com.insurance.model.InsuranceCategoryData;
import com.insurance.util.Response;

public interface IInsuranceCategoryService {

	Response addInsurance(InsuranceDTO insuranceDTO, String access);

	Response updateInsurance(String token, InsuranceDTO insuranceDTO, String access);

	List<InsuranceCategoryData> getAllInsurance(String token, String access);

	Response deleteInsurance(String token, String access);

	List<InsuranceCategoryData> getAllInsuranceWithParticularCategory(String token, String access);

	List<InsuranceCategoryData> getAllInsuranceBetweenDates(String token, String date1, String date2, String access);
}
