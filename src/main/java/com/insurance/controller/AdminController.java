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
import com.insurance.model.UserData;
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
	@GetMapping("/getallusers/{token}")
	public ResponseEntity<List<?>> getAllUsers(@PathVariable String token) {
		log.info("Get All User Data");
		List<UserData> response = userservice.getAllUsers(token);
		return new ResponseEntity<List<?>>(response, HttpStatus.OK);
	}
	
	//Returns all the insurance data present
	@GetMapping("/getallcateogoryinsurance/{token}")
	public ResponseEntity<List<?>> getAllCategoryInsurance(@PathVariable String token) {
		log.info("Get All Insurance Data");
		List<InsuranceCategoryData> response = insuranceservice.getAllInsurance(token);
		return new ResponseEntity<List<?>>(response, HttpStatus.OK);
	}
	
	//Returns all the data present
	@GetMapping("/getallcreatedinsurance/{token}")
	public ResponseEntity<List<?>> getAllCreatedInsurance(@PathVariable String token) {
		log.info("Get All Insurance Create Data");
		List<?> response = insurancecreateservice.getAllInsurance(token);
		return new ResponseEntity<List<?>>(response, HttpStatus.OK);
	}
	
	//Returns all the user data registered between dates
	@GetMapping("/getalluserbetweendates/{token}")
	public ResponseEntity<List<?>> getAllUserBetweenRegisteredData(@PathVariable String token,
																   @RequestParam String date1,
																   @RequestParam String date2) {
		log.info("Get All User Data Registered Between Dates");
		List<UserData> response = userservice.getAllUserBetweenRegisteredDate(token,date1,date2);
		return new ResponseEntity<List<?>>(response, HttpStatus.OK);
	}

	//Returns all the user data based on health condition
	@GetMapping("/getalluserwithhealthcondition/{token}")
	public ResponseEntity<List<?>> getAllUserWithHealthCondition(@PathVariable String token,
																 @RequestParam String healthCondition) {
		log.info("Get All User Data Based On Health Condition");
		List<UserData> response = userservice.getAllUserWithHealthCondition(token, healthCondition);
		return new ResponseEntity<List<?>>(response, HttpStatus.OK);
	}
		
	//Returns all the user data based on vehicle data
	@GetMapping("/getalluserwithvehicledata/{token}")
	public ResponseEntity<List<?>> getAllUserWithVehicleData(@PathVariable String token,
															 @RequestParam String vehicleData) {
		log.info("Get All User Data Based On Vehicle Data");
		List<UserData> response = userservice.getAllUserWithVehicleData(token, vehicleData);
		return new ResponseEntity<List<?>>(response, HttpStatus.OK);
	}
	
	//Returns all the insurance data based on id
	@GetMapping("/getallinsurancewithid/{token}")
	public ResponseEntity<List<?>> getAllInsuranceWithParticularCategory(@PathVariable String token) {
		log.info("Get All Insurance Data Based On Id");
		List<InsuranceCategoryData> response = insuranceservice.getAllInsuranceWithParticularCategory(token);
		return new ResponseEntity<List<?>>(response, HttpStatus.OK);
	}
		
	//Returns all the insurance data between dates
	@GetMapping("/getallinsurancebetweendates/{token}")
	public ResponseEntity<List<?>> getAllInsuranceBetweenDates(@PathVariable String token,
															   @RequestParam String date1,
															   @RequestParam String date2) {
		log.info("Get All Insurance Data Between Dates");
		List<InsuranceCategoryData> response = insuranceservice.getAllInsuranceBetweenDates(token,date1,date2);
		return new ResponseEntity<List<?>>(response, HttpStatus.OK);
	}
}
