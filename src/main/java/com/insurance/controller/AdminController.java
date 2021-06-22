package com.insurance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.model.InsuranceCategoryData;
import com.insurance.model.UserRegistrationData;
import com.insurance.service.IInsuranceCategoryService;
import com.insurance.service.IInsuranceCreateService;
import com.insurance.service.IUserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminController {

	@Autowired
	private IUserService userservice;
	
	@Autowired
	private IInsuranceCategoryService insuranceservice;
	
	@Autowired
	private IInsuranceCreateService insurancecreateservice;
	
	//Returns all the user data present
	@GetMapping("/getallusers/{access}")
	public ResponseEntity<List<?>> getAllUsers(@RequestParam String token,
												@PathVariable String access) {
		log.info("Get All User Data");
		List<UserRegistrationData> response = userservice.getAllUsers(token,access);
		return new ResponseEntity<List<?>>(response, HttpStatus.OK);
	}
	
	//Returns all the insurance data present
	@GetMapping("/getallcateogoryinsurance/{access}")
	public ResponseEntity<List<?>> getAllCategoryInsurance(@RequestParam String token,
															@PathVariable String access) {
		log.info("Get All Insurance Data");
		List<InsuranceCategoryData> response = insuranceservice.getAllInsurance(token,access);
		return new ResponseEntity<List<?>>(response, HttpStatus.OK);
	}
	
	//Returns all the data present
	@GetMapping("/getallcreatedinsurance/{access}")
	public ResponseEntity<List<?>> getAllCreatedInsurance(@RequestParam String token,
															@PathVariable String access) {
		log.info("Get All Insurance Create Data");
		List<?> response = insurancecreateservice.getAllInsurance(token,access);
		return new ResponseEntity<List<?>>(response, HttpStatus.OK);
	}
	
	//Returns all the user data registered between dates
	@GetMapping("/getalluserbetweendates/{access}")
	public ResponseEntity<List<?>> getAllUserBetweenRegisteredData(@RequestParam String token,
																   @RequestParam String date1,
																   @RequestParam String date2,
																   @PathVariable String access) {
		log.info("Get All User Data Registered Between Dates");
		List<UserRegistrationData> response = userservice.getAllUserBetweenRegisteredDate(token,date1,date2,access);
		return new ResponseEntity<List<?>>(response, HttpStatus.OK);
	}

	//Returns all the user data based on health condition
	@GetMapping("/getalluserwithhealthcondition/{access}")
	public ResponseEntity<List<?>> getAllUserWithHealthCondition(@RequestParam String token,
																 @RequestParam String healthCondition,
																 @PathVariable String access) {
		log.info("Get All User Data Based On Health Condition");
		List<UserRegistrationData> response = userservice.getAllUserWithHealthCondition(token, healthCondition, access);
		return new ResponseEntity<List<?>>(response, HttpStatus.OK);
	}
		
	//Returns all the user data based on vehicle data
	@GetMapping("/getalluserwithvehicledata/{access}")
	public ResponseEntity<List<?>> getAllUserWithVehicleData(@RequestParam String token,
															 @RequestParam String vehicleData,
															 @PathVariable String access) {
		log.info("Get All User Data Based On Vehicle Data");
		List<UserRegistrationData> response = userservice.getAllUserWithVehicleData(token, vehicleData, access);
		return new ResponseEntity<List<?>>(response, HttpStatus.OK);
	}
	
	//Returns all the insurance data based on id
	@GetMapping("/getallinsurancewithid/{access}")
	public ResponseEntity<List<?>> getAllInsuranceWithParticularCategory(@RequestParam String token,
																		@PathVariable String access) {
		log.info("Get All Insurance Data Based On Id");
		List<InsuranceCategoryData> response = insuranceservice.getAllInsuranceWithParticularCategory(token, access);
		return new ResponseEntity<List<?>>(response, HttpStatus.OK);
	}
		
	//Returns all the insurance data between dates
	@GetMapping("/getallinsurancebetweendates/{access}")
	public ResponseEntity<List<?>> getAllInsuranceBetweenDates(@RequestParam String token,
															   @RequestParam String date1,
															   @RequestParam String date2,
															   @PathVariable String access) {
		log.info("Get All Insurance Data Between Dates");
		List<InsuranceCategoryData> response = insuranceservice.getAllInsuranceBetweenDates(token,date1,date2,access);
		return new ResponseEntity<List<?>>(response, HttpStatus.OK);
	}
}
