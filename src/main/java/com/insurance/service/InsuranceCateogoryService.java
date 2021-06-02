package com.insurance.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.dto.InsuranceDTO;
import com.insurance.exception.UserInsuranceException;
import com.insurance.model.InsuranceCategoryData;
import com.insurance.repository.InsuranceRepository;
import com.insurance.util.Response;
import com.insurance.util.TokenUtil;

@Service
public class InsuranceCateogoryService implements IInsuranceCategoryService{
	
	
	@Autowired
	private InsuranceRepository insuranceRepository;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private TokenUtil tokenUtil;

	@Override
	public List<InsuranceCategoryData> getAllInsurance(String token) {
		int id = tokenUtil.decodeToken(token);
		Optional<InsuranceCategoryData> isPresent = insuranceRepository.findById(id);
		if(isPresent.isPresent()) {
			List<InsuranceCategoryData> getAllInsurance = insuranceRepository.findAll();
			return getAllInsurance;
		}else {
			throw new UserInsuranceException(400, "Token is not Valid");
		}	
	}

	@Override
	public Response addInsurance(InsuranceDTO insuranceDTO) {
		Optional<InsuranceCategoryData> isPresent = insuranceRepository.findByInsuranceCode(insuranceDTO.getInsuranceCode());
		if(isPresent.isPresent()) {
			throw new UserInsuranceException(400, "Contact Already Added");
		}else {
			InsuranceCategoryData user = modelmapper.map(insuranceDTO, InsuranceCategoryData.class);
			insuranceRepository.save(user);
			String token = tokenUtil.createToken(user.getInsuranceId());
			return new Response(200, "Contact Added Successfully", token);
		}	
	}

	@Override
	public Response updateInsurance(String token, InsuranceDTO insuranceDTO) {
		
		int id = tokenUtil.decodeToken(token);
		Optional<InsuranceCategoryData> isPresent = insuranceRepository.findById(id);
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
		Optional<InsuranceCategoryData> isPresent = insuranceRepository.findById(id);
		if(isPresent.isPresent()) {
			insuranceRepository.delete(isPresent.get());
			return new Response(200, "Contact Deleted Successfully", token);
		}else {
			throw new UserInsuranceException(400, "Token is not Valid");
		}
	}

}

