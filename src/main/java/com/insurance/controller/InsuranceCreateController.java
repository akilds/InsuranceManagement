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
import com.insurance.model.InsuranceCreateData;
import com.insurance.service.IInsuranceCreateService;
import com.insurance.util.Response;

@RestController
@RequestMapping("/insurancecreate")
public class InsuranceCreateController {

	@Autowired
	private IInsuranceCreateService insuranceservice;
	
	@GetMapping("/getallinsurance/{token}")
	public ResponseEntity<List<?>> getAllInsurance(@PathVariable String token) {
		List<InsuranceCreateData> response = insuranceservice.getAllInsurance(token);
		return new ResponseEntity<List<?>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/getallinsurancebystatus/{token}")
	public ResponseEntity<List<?>> getAllByStatus(@PathVariable String token,
												  @RequestParam String status) {
		List<?> response = insuranceservice.getAllByStatus(status,token);
		return new ResponseEntity<List<?>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/getallinsurancebymonthperiod/{token}")
	public ResponseEntity<List<?>> getAllByMonthPeriod(@PathVariable String token,
													   @RequestParam int monthPeriod) {
		List<?> response = insuranceservice.getAllByMonthPeriod(monthPeriod,token);
		return new ResponseEntity<List<?>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/getallinsuranceforuser/{token}")
	public ResponseEntity<List<?>> getAllInsuranceForUser(@PathVariable String token,
														  @RequestParam String name) {
		List<?> response = insuranceservice.getAllInsuranceForUser(name,token);
		return new ResponseEntity<List<?>>(response, HttpStatus.OK);
	}
	
	@PostMapping("/addnewinsurance")
	public ResponseEntity<Response> createInsurance(@RequestParam String userToken,
													@RequestParam String insuranceToken,
													@Valid @RequestBody InsuranceCreateDTO insuranceDTO) {
		Response response  = insuranceservice.addInsurance(userToken,insuranceToken,insuranceDTO);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@PutMapping("/updateinsurance/{token}")
	public ResponseEntity<Response> updateInsurance(@PathVariable String token,
												  	@Valid @RequestBody InsuranceCreateDTO insuranceDTO) {
		Response response  = insuranceservice.updateInsurance(token, insuranceDTO);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteinsurance")
	public ResponseEntity<Response> deleteInsurance(@RequestParam String token) {
		Response response  = insuranceservice.deleteInsurance(token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
}
