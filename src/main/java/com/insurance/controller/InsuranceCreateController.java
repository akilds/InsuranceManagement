package com.insurance.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.dto.InsuranceCreateDTO;
import com.insurance.service.IInsuranceCreateService;
import com.insurance.util.Response;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/insurancecreate")
@Slf4j
public class InsuranceCreateController {

	@Autowired
	private IInsuranceCreateService insuranceservice;
	
	//Returns all the data present
	@GetMapping("/getallinsurance/{token}")
	public ResponseEntity<List<?>> getAllInsurance(@PathVariable String token) {
		log.info("Get All Insurance Create Data");
		List<?> response = insuranceservice.getAllInsurance(token);
		return new ResponseEntity<List<?>>(response, HttpStatus.OK);
	}
	
	//Returns all data based on status
	@GetMapping("/getallinsurancebystatus/{token}")
	public ResponseEntity<List<?>> getAllByStatus(@PathVariable String token,
												  @RequestParam String status) {
		log.info("Get Data Based On Status");
		List<?> response = insuranceservice.getAllByStatus(status,token);
		return new ResponseEntity<List<?>>(response, HttpStatus.OK);
	}
	
	//Returns all data based on monthPeriod
	@GetMapping("/getallinsurancebymonthperiod/{token}")
	public ResponseEntity<List<?>> getAllByMonthPeriod(@PathVariable String token,
													   @RequestParam int monthPeriod) {
		log.info("Get Data Based On Month Period");
		List<?> response = insuranceservice.getAllByMonthPeriod(monthPeriod,token);
		return new ResponseEntity<List<?>>(response, HttpStatus.OK);
	}
	
	//Returns all data based on fullName
	@GetMapping("/getallinsuranceforuser/{token}")
	public ResponseEntity<List<?>> getAllInsuranceForUser(@PathVariable String token,
														  @RequestParam String name) {
		log.info("Get Data Based On First Name");
		List<?> response = insuranceservice.getAllInsuranceForUser(name,token);
		return new ResponseEntity<List<?>>(response, HttpStatus.OK);
	}
	
	//Returns all data based on claimed insurance
	@GetMapping("/getallclaimedinsurance/{token}")
	public ResponseEntity<List<?>> getAllClaimedInsurance(@PathVariable String token) {
		log.info("Get Data Based On Claimed Insurance");
		List<?> response = insuranceservice.getAllClaimedInsurance(token);
		return new ResponseEntity<List<?>>(response, HttpStatus.OK);
	}
		
	//Creates a new insurance create data
	@PostMapping("/addnewinsurance")
	public ResponseEntity<Response> createInsurance(@Valid @RequestBody InsuranceCreateDTO insuranceDTO) {
		log.info("Create Insurance : " + insuranceDTO);
		Response response  = insuranceservice.addInsurance(insuranceDTO);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	//Updates an existing insurance create data
	@PutMapping("/updateinsurance/{token}")
	public ResponseEntity<Response> updateInsurance(@PathVariable String token,
												  	@Valid @RequestBody InsuranceCreateDTO insuranceDTO,
												  	@RequestParam boolean claimed) {
		log.info("Update Insurance Create Data: " + insuranceDTO);
		Response response  = insuranceservice.updateInsurance(token, insuranceDTO, claimed);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	//Deletes an existing insurance create data
	@DeleteMapping("/deleteinsurance")
	public ResponseEntity<Response> deleteInsurance(@RequestParam String token) {
		log.info("Insurance Create Data Deleted");
		Response response  = insuranceservice.deleteInsurance(token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
}
