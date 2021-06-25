package com.insurance.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InsuranceCateogoryService implements IInsuranceCategoryService{
	
	@Autowired
	private InsuranceRepository insuranceRepository;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private TokenUtil tokenUtil;
	
	private enum ACCESS{
		admin;
	}

	//Returns all the insurance data
	@Override
	public List<InsuranceCategoryData> getAllInsurance(String token, String access) {
		int id = tokenUtil.decodeToken(token);
		Optional<InsuranceCategoryData> isPresent = insuranceRepository.findById(id);
		if(access.equals(ACCESS.admin.name())) {
			if(isPresent.isPresent()) {
				log.info("Get All Insurance Data");
				List<InsuranceCategoryData> getAllInsurance = insuranceRepository.findAll();
				return getAllInsurance;
			}else {
				log.error("Insurance Token Is Not Valid");
				throw new UserInsuranceException(400, "Insurance Token Is Not Valid");
			}	
		}else {
			log.error("Not Authorised");
			throw new UserInsuranceException(400, "Not Authorised");
		}	
	}

	//Returns all the insurance data for id
	@Override
	public List<InsuranceCategoryData> getAllInsuranceWithParticularCategory(String token, String access) {
		int id = tokenUtil.decodeToken(token);
		Optional<InsuranceCategoryData> isPresent = insuranceRepository.findById(id);
		if(access.equals(ACCESS.admin.name())) {
			if(isPresent.isPresent()) {
				log.info("Get All Insurance Data Fot id");
				List<InsuranceCategoryData> getAllInsurance = insuranceRepository.findAllByInsuranceId(id);
				return getAllInsurance;
			}else {
				log.error("Insurance Token Is Not Valid");
				throw new UserInsuranceException(400, "Insurance Token Is Not Valid");
			}	
		}else {
			log.error("Not Authorised");
			throw new UserInsuranceException(400, "Not Authorised");
		}	
	}
		
	//Returns all the insurance data between dates
	@Override
	public List<InsuranceCategoryData> getAllInsuranceBetweenDates(String token,String date1,
																   String date2,String access) {
		int id = tokenUtil.decodeToken(token);
		Optional<InsuranceCategoryData> isPresent = insuranceRepository.findById(id);
		if(access.equals(ACCESS.admin.name())) {
			if(isPresent.isPresent()) {
				log.info("Get All Insurance Data Between Dates");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); 
				LocalDateTime start = LocalDateTime.parse(date1, formatter);
				LocalDateTime end = LocalDateTime.parse(date2, formatter);
				List<InsuranceCategoryData> getAllInsurance = insuranceRepository.findAllInsuranceBetweenDates(start,end);
				return getAllInsurance;
			}else {
				log.error("Insurance Token Is Not Valid");
				throw new UserInsuranceException(400, "Insurance Token Is Not Valid");
			}
		}else {
			log.error("Not Authorised");
			throw new UserInsuranceException(400, "Not Authorised");
		}	
	}
	
	//Adds a new insurance data
	@Override
	public Response addInsurance(InsuranceDTO insuranceDTO, String access) {
		Optional<InsuranceCategoryData> isPresent = insuranceRepository.findByInsuranceCode(insuranceDTO.getInsuranceCode());
		if(access.equals(ACCESS.admin.name())) {
			if(isPresent.isPresent()) {
				log.error("Insurance Data Already Added");
				throw new UserInsuranceException(400, "Insurance Data Already Added");
			}else {
				log.info("Insurance Data Added : " + insuranceDTO);
				InsuranceCategoryData user = modelmapper.map(insuranceDTO, InsuranceCategoryData.class);
				insuranceRepository.save(user);
				String token = tokenUtil.createToken(user.getInsuranceId());
				return new Response(200, "Insurance Data Added Successfully", token);
			}
		}else {
			log.error("Not Authorised");
			throw new UserInsuranceException(400, "Not Authorised");
		}	
	}

	//Updates an existing insurance data
	@Override
	public Response updateInsurance(String token, InsuranceDTO insuranceDTO, String access) {
		int id = tokenUtil.decodeToken(token);
		Optional<InsuranceCategoryData> isPresent = insuranceRepository.findById(id);
		if(access.equals(ACCESS.admin.name())) {
			if(isPresent.isPresent()) {
				log.info("Insurance Data Updated : " + insuranceDTO);
				isPresent.get().updateInsurance(insuranceDTO);
				insuranceRepository.save(isPresent.get());
				return new Response(200, "Insurabce Data Updated Successfully", token);
			}else {
				log.error("Insurance Data Doesnt Exist");
				throw new UserInsuranceException(400, "Insurancce Data Doesnt Exist");
			}
		}else {
			log.error("Not Authorised");
			throw new UserInsuranceException(400, "Not Authorised");
		}	
	}

	//Deletes an existing insurance data
	@Override
	public Response deleteInsurance(String token, String access) {
		int id = tokenUtil.decodeToken(token);
		Optional<InsuranceCategoryData> isPresent = insuranceRepository.findById(id);
		if(access.equals(ACCESS.admin.name())) {
			if(isPresent.isPresent()) {
				log.info("Insurance Data Deleted Successfully");
				insuranceRepository.delete(isPresent.get());
				return new Response(200, "Insurance Data Deleted Successfully", token);
			}else {
				log.error("Insurance Token Is Not Valid");
				throw new UserInsuranceException(400, "Insurance Token Is Not Valid");
			}
		}else {
			log.error("Not Authorised");
			throw new UserInsuranceException(400, "Not Authorised");
		}
	}

}

