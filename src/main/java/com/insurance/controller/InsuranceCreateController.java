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
	@GetMapping("/getallinsurance/{access}")
	public ResponseEntity<List<?>> getAllInsurance(@RequestParam String token,
													@PathVariable String access) {
		log.info("Get All Insurance Create Data");
		List<?> response = insuranceservice.getAllInsurance(token,access);
		return new ResponseEntity<List<?>>(response, HttpStatus.OK);
	}
	
	//Returns all data based on status
	@GetMapping("/getallinsurancebystatus/{access}")
	public ResponseEntity<List<?>> getAllByStatus(@RequestParam String token,
												  @RequestParam String status,
												  @PathVariable String access) {
		log.info("Get Data Based On Status");
		List<?> response = insuranceservice.getAllByStatus(status,token,access);
		return new ResponseEntity<List<?>>(response, HttpStatus.OK);
	}
	
	//Returns all data based on monthPeriod
	@GetMapping("/getallinsurancebymonthperiod/{access}")
	public ResponseEntity<List<?>> getAllByMonthPeriod(@RequestParam String token,
													   @RequestParam int monthPeriod,
													   @PathVariable String access) {
		log.info("Get Data Based On Month Period");
		List<?> response = insuranceservice.getAllByMonthPeriod(monthPeriod,token,access);
		return new ResponseEntity<List<?>>(response, HttpStatus.OK);
	}
	
	//Returns all data based on fullName
	@GetMapping("/getallinsuranceforuser/{access}")
	public ResponseEntity<List<?>> getAllInsuranceForUser(@RequestParam String token,
														  @RequestParam String name,
														  @PathVariable String access) {
		log.info("Get Data Based On First Name");
		List<?> response = insuranceservice.getAllInsuranceForUser(name,token,access);
		return new ResponseEntity<List<?>>(response, HttpStatus.OK);
	}
	
	//Returns all data based on claimed insurance
	@GetMapping("/getallclaimedinsurance/{access}")
	public ResponseEntity<List<?>> getAllClaimedInsurance(@RequestParam String token,
														  @PathVariable String access) {
		log.info("Get Data Based On Claimed Insurance");
		List<?> response = insuranceservice.getAllClaimedInsurance(token,access);
		return new ResponseEntity<List<?>>(response, HttpStatus.OK);
	}
		
	//Creates a new insurance create data
	@PostMapping("/addnewinsurance/{access}")
	public ResponseEntity<Response> createInsurance(@Valid @RequestBody InsuranceCreateDTO insuranceDTO,
													@PathVariable String access) {
		log.info("Create Insurance : " + insuranceDTO);
		Response response  = insuranceservice.addInsurance(insuranceDTO,access);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	//Updates an existing insurance create data
	@PutMapping("/updateinsurance/{access}")
	public ResponseEntity<Response> updateInsurance(@RequestParam String token,
												  	@Valid @RequestBody InsuranceCreateDTO insuranceDTO,
												  	@RequestParam boolean claimed,
												  	@PathVariable String access) {
		log.info("Update Insurance Create Data: " + insuranceDTO);
		Response response  = insuranceservice.updateInsurance(token, insuranceDTO, claimed, access);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	//Deletes an existing insurance create data
	@DeleteMapping("/deleteinsurance/{access}")
	public ResponseEntity<Response> deleteInsurance(@RequestParam String token,
													@PathVariable String access) {
		log.info("Insurance Create Data Deleted");
		Response response  = insuranceservice.deleteInsurance(token,access);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
}
