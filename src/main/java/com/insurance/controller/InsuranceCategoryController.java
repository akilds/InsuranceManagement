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

import com.insurance.dto.InsuranceDTO;
import com.insurance.model.InsuranceCategoryData;
import com.insurance.service.IInsuranceCategoryService;
import com.insurance.util.Response;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/insurance")
@Slf4j
public class InsuranceCategoryController {

	@Autowired
	private IInsuranceCategoryService insuranceservice;
	
	//Returns all the insurance data present
	@GetMapping("/getallinsurance/{access}")
	public ResponseEntity<List<?>> getAllInsurance(@RequestParam String token,
													@PathVariable String access) {
		log.info("Get All Insurance Data");
		List<InsuranceCategoryData> response = insuranceservice.getAllInsurance(token,access);
		return new ResponseEntity<List<?>>(response, HttpStatus.OK);
	}
	
	//Creates a new insurance data
	@PostMapping("/addnewinsurance/{access}")
	public ResponseEntity<Response> createInsurance(@Valid @RequestBody InsuranceDTO insuranceDTO,
													@PathVariable String access) {
		log.info("Create Insurance : " + insuranceDTO);
		Response response  = insuranceservice.addInsurance(insuranceDTO, access);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	//Updates an existing insurance data
	@PutMapping("/updateinsurance/{access}")
	public ResponseEntity<Response> updateInsurance(@RequestParam String token,
												  @Valid @RequestBody InsuranceDTO insuranceDTO,
												  @PathVariable String access) {
		log.info("Update User : " + insuranceDTO);
		Response response  = insuranceservice.updateInsurance(token, insuranceDTO, access);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	//Deletes an existing insurance data
	@DeleteMapping("/deleteinsurance/{access}")
	public ResponseEntity<Response> deleteInsurance(@RequestParam String token,
													@PathVariable String access) {
		log.info("Deleted Insurance Data");
		Response response  = insuranceservice.deleteInsurance(token, access);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
}
