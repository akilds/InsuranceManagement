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

@RestController
@RequestMapping("/insurance")
public class InsuranceCategoryController {

	@Autowired
	private IInsuranceCategoryService insuranceservice;
	
	@GetMapping("/getallinsurance/{token}")
	public ResponseEntity<List<?>> getAllInsurance(@PathVariable String token) {
		List<InsuranceCategoryData> response = insuranceservice.getAllInsurance(token);
		return new ResponseEntity<List<?>>(response, HttpStatus.OK);
	}
	
	@PostMapping("/addnewinsurance")
	public ResponseEntity<Response> createInsurance(@Valid @RequestBody InsuranceDTO insuranceDTO) {
		Response response  = insuranceservice.addInsurance(insuranceDTO);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@PutMapping("/updateinsurance/{token}")
	public ResponseEntity<Response> updateInsurance(@PathVariable String token,
												  @Valid @RequestBody InsuranceDTO userDTO) {
		Response response  = insuranceservice.updateInsurance(token, userDTO);
		return new ResponseEntity<Response>(response, HttpStatus
				.OK);
	}
	
	@DeleteMapping("/deleteinsurance")
	public ResponseEntity<Response> deleteInsurance(@RequestParam String token) {
		Response response  = insuranceservice.deleteInsurance(token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
}
