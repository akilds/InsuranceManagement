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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.dto.UserDTO;
import com.insurance.model.UserRegistrationData;
import com.insurance.service.IUserService;
import com.insurance.util.Response;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/useri")
@Slf4j
public class UserController {

	@Autowired
	private IUserService userService;
	
	//Returns all the user data present
	@GetMapping("/getallusers/{access}")
	public ResponseEntity<List<?>> getAllUsers(@RequestHeader String token,
											   @PathVariable String access) {
		log.info("Get All User Data");
		List<UserRegistrationData> response = userService.getAllUsers(token,access);
		return new ResponseEntity<List<?>>(response, HttpStatus.OK);
	}
	
	//Creates a new user data 
	@PostMapping("/addnewuser/{access}")
	public ResponseEntity<Response> createUser(@Valid @RequestBody UserDTO userDTO,
												@PathVariable String access) {
		log.info("Create User Data : " + userDTO);
		Response response  = userService.addUser(userDTO,access);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	//User Login
	@PutMapping("/login")
	public ResponseEntity<Response> userLogin(@RequestHeader String token,
											  @RequestParam String emailId,
											  @RequestParam String password) {
		log.info("User Login");
		Response response  = userService.userLogin(token,emailId,password);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
		
	//Changes the forget password
	@PutMapping("/forgetpassword")
	public ResponseEntity<Response> forgetPassword(@RequestHeader String token,
											  	   @RequestParam String emailId,
											  	   @RequestParam String password) {
		log.info("Forgot Password");
		Response response  = userService.forgetPassword(token,emailId,password);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
		
	//Updates an existing user data
	@PutMapping("/updateuser/{access}")
	public ResponseEntity<Response> updateUser(@RequestHeader String token,
											   @Valid @RequestBody UserDTO userDTO,
											   @PathVariable String access) {
		log.info("Update User Data : " + userDTO);
		Response response  = userService.updateUser(token, userDTO, access);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	//Deletes an existing user data
	@DeleteMapping("/deleteuser/{access}")
	public ResponseEntity<Response> deleteUser(@RequestHeader String token,
												@PathVariable String access) {
		log.info("User Data Deleted");
		Response response  = userService.deleteUser(token, access);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
}
