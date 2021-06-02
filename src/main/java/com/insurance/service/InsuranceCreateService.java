package com.insurance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.dto.InsuranceCreateDTO;
import com.insurance.exception.UserInsuranceException;
import com.insurance.model.InsuranceCreateData;
import com.insurance.repository.InsuranceCreateRepository;
import com.insurance.util.Response;
import com.insurance.util.TokenUtil;

@Service
public class InsuranceCreateService implements IInsuranceCreateService{

	@Autowired
	private InsuranceCreateRepository insuranceRepository;
	
	@Autowired
	private TokenUtil tokenUtil;

	@Override
	public List<InsuranceCreateData> getAllInsurance(String token) {
		int id = tokenUtil.decodeToken(token);
		Optional<InsuranceCreateData> isPresent = insuranceRepository.findById(id);
		if(isPresent.isPresent()) {
			List<InsuranceCreateData> getAllInsurance = insuranceRepository.findAll();
			return getAllInsurance;
		}else {
			throw new UserInsuranceException(400, "Token is not Valid");
		}	
	}

	@Override
	public List<?> getAllByStatus(String status, String token) {
		int id = tokenUtil.decodeToken(token);
		Optional<InsuranceCreateData> isPresent = insuranceRepository.findById(id);
		if(isPresent.isPresent()) {
			return insuranceRepository.findAllByStatus(status);
		}else {
			throw new UserInsuranceException(400, "Token is not Valid");
		}	
	}
	
	@Override
	public List<?> getAllByMonthPeriod(int monthPeriod, String token) {
		int id = tokenUtil.decodeToken(token);
		Optional<InsuranceCreateData> isPresent = insuranceRepository.findById(id);
		if(isPresent.isPresent()) {
			return insuranceRepository.findAllByMonthPeriod(monthPeriod);
		}else {
			throw new UserInsuranceException(400, "Token is not Valid");
		}	
	}
	
	@Override
	public List<?> getAllInsuranceForUser(String fullName, String token) {
		int id = tokenUtil.decodeToken(token);
		Optional<InsuranceCreateData> isPresent = insuranceRepository.findById(id);
		if(isPresent.isPresent()) {
			return insuranceRepository.findAllInsuranceForUser(fullName);
		}else {
			throw new UserInsuranceException(400, "Token is not Valid");
		}	
	}
	
	@Override
	public Response addInsurance(String userToken,String insuranceToken,InsuranceCreateDTO insuranceDTO) {
		int userId = tokenUtil.decodeToken(userToken);
		int insuranceId = tokenUtil.decodeToken(insuranceToken);
		InsuranceCreateData user = new InsuranceCreateData(userId, insuranceId, insuranceDTO);
		insuranceRepository.save(user);
		String token = tokenUtil.createToken(user.getId());
		return new Response(200, "Contact Added Successfully", token);	
	}

	@Override
	public Response updateInsurance(String token, InsuranceCreateDTO insuranceDTO) {
		
		int id = tokenUtil.decodeToken(token);
		Optional<InsuranceCreateData> isPresent = insuranceRepository.findById(id);
		if(isPresent.isPresent()) {
			isPresent.get().updateInsurance(insuranceDTO);
			
			insuranceRepository.save(isPresent.get());
			return new Response(200, "Contact Updated Successfully", token);
		}else {
			throw new UserInsuranceException(400, "Contact Doesnt Exist");
		}	
	}

	@Override
	public Response deleteInsurance(String token) {
		int id = tokenUtil.decodeToken(token);
		Optional<InsuranceCreateData> isPresent = insuranceRepository.findById(id);
		if(isPresent.isPresent()) {
			insuranceRepository.delete(isPresent.get());
			return new Response(200, "Contact Deleted Successfully", token);
		}else {
			throw new UserInsuranceException(400, "Token is not Valid");
		}
	}
}
