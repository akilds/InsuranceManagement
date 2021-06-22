package com.insurance.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.dto.InsuranceCreateDTO;
import com.insurance.exception.UserInsuranceException;
import com.insurance.model.InsuranceCreateData;
import com.insurance.repository.InsuranceCreateRepository;
import com.insurance.util.Response;
import com.insurance.util.TokenUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InsuranceCreateService implements IInsuranceCreateService{

	@Autowired
	private InsuranceCreateRepository insuranceRepository;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private TokenUtil tokenUtil;

	//Returns all the insurance create data present
	@Override
	public List<?> getAllInsurance(String token, String access) {
		int id = tokenUtil.decodeToken(token);
		Optional<InsuranceCreateData> isPresent = insuranceRepository.findById(id);
		if(isPresent.isPresent() && access.equals("handler")) {
			log.info("Get All Data");
			return insuranceRepository.findAllData();
		}else {
			log.error("Insurance Create Token Is Not Valid/Not Authorised");
			throw new UserInsuranceException(400, "Insurance Create Token Is Not Valid/Not Authorised");
		}	
	}

	//Returns all the data based on status
	@Override
	public List<?> getAllByStatus(String status, String token, String access) {
		int id = tokenUtil.decodeToken(token);
		Optional<InsuranceCreateData> isPresent = insuranceRepository.findById(id);
		if(isPresent.isPresent() && access.equals("handler")) {
			log.info("Get All Data Based On Status");
			return insuranceRepository.findAllByStatus(status);
		}else {
			log.error("Insurance Create Token Is Not Valid/Not Authorised");
			throw new UserInsuranceException(400, "Insurance Create Token Is Not Valid/Not Authorised");
		}	
	}

	//Returns all the data based on month period
	@Override
	public List<?> getAllByMonthPeriod(int monthPeriod, String token, String access) {
		int id = tokenUtil.decodeToken(token);
		Optional<InsuranceCreateData> isPresent = insuranceRepository.findById(id);
		if(isPresent.isPresent() && access.equals("handler")) {
			log.info("Get All Data Based On Month Period");
			return insuranceRepository.findAllByMonthPeriod(monthPeriod);
		}else {
			log.error("Insurance Create Token Is Not Valid/Not Authorised");
			throw new UserInsuranceException(400, "Insurance Create Token Is Not Valid/Not Authorised");
		}	
	}
	
	//Returns all the data based on full name
	@Override
	public List<?> getAllInsuranceForUser(String fullName, String token, String access) {
		int id = tokenUtil.decodeToken(token);
		Optional<InsuranceCreateData> isPresent = insuranceRepository.findById(id);
		if(isPresent.isPresent() && access.equals("handler")) {
			log.info("Get All Data Based On Full Name");
			return insuranceRepository.findAllInsuranceForUser(fullName);
		}else {
			log.error("Insurance Create Token Is Not Valid/Not Authorised");
			throw new UserInsuranceException(400, "Insurance Create Token Is Not Valid/Not Authorised");
		}	
	}
	
	//Returns all the data based on claimed insurance
	@Override
	public List<?> getAllClaimedInsurance(String token, String access) {
		int id = tokenUtil.decodeToken(token);
		Optional<InsuranceCreateData> isPresent = insuranceRepository.findById(id);
		if(isPresent.isPresent() && access.equals("handler")) {
			log.info("Get All Claimed Insurance Data");
			return insuranceRepository.findAllClaimedInsuranceData(true);
		}else {
			log.error("Insurance Create Token Is Not Valid/Not Authorised");
			throw new UserInsuranceException(400, "Insurance Create Token Is Not Valid/Not Authorised");
		}	
	}
	
	//Adds new insurance create data
	@Override
	public Response addInsurance(InsuranceCreateDTO insuranceDTO, String access) {
		if(access.equals("handler")) {
			log.info("Added Insurance Create Data : " + insuranceDTO);
			InsuranceCreateData user = modelmapper.map(insuranceDTO, InsuranceCreateData.class);
			insuranceRepository.save(user);
			String token = tokenUtil.createToken(user.getId());
			return new Response(200, "Insurance Create Data Added Successfully", token);	
		}else {
			log.error("Not Authorised");
			throw new UserInsuranceException(400, "Not Authorised");
		}
	}

	//Updates an existing insurance create data
	@Override
	public Response updateInsurance(String token, InsuranceCreateDTO insuranceDTO, boolean claimed, String access) {
		int id = tokenUtil.decodeToken(token);
		Optional<InsuranceCreateData> isPresent = insuranceRepository.findById(id);
		if(isPresent.isPresent() && access.equals("handler")) {
			log.info("Updated Insurance Create Data : " + insuranceDTO);
			isPresent.get().updateInsurance(insuranceDTO,claimed);
			insuranceRepository.save(isPresent.get());
			return new Response(200, "Insurance Create Data Updated Successfully", token);
		}else {
			log.error("Insurance Create Data Doesnt Exist/Not Authorised");
			throw new UserInsuranceException(400, "Insurance Create Data Doesnt Exist/Not Authorised");
		}	
	}

	//deletes an existing insurance create data
	@Override
	public Response deleteInsurance(String token, String access) {
		int id = tokenUtil.decodeToken(token);
		Optional<InsuranceCreateData> isPresent = insuranceRepository.findById(id);
		if(isPresent.isPresent() && access.equals("handler")) {
			log.info("Insurance Create Data Deleted");
			insuranceRepository.delete(isPresent.get());
			return new Response(200, "Insurance Create Data Deleted Successfully", token);
		}else {
			log.error("Insurance Create Token Is Not Valid/Not Authorised");
			throw new UserInsuranceException(400, "Insurance Create Token Is Not Valid/Not Authorised");
		}
	}
}
