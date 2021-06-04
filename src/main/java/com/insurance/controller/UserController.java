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

import com.insurance.dto.UserDTO;
import com.insurance.model.UserData;
import com.insurance.service.IUserService;
import com.insurance.util.Response;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/useri")
@Slf4j
public class UserController {

	@Autowired
	private IUserService userservice;
	
	//Returns all the user data present
	@GetMapping("/getallusers/{token}")
	public ResponseEntity<List<?>> getAllUsers(@PathVariable String token) {
		log.info("Get All User Data");
		List<UserData> response = userservice.getAllUsers(token);
		return new ResponseEntity<List<?>>(response, HttpStatus.OK);
	}
	
	//Creates a new user data 
	@PostMapping("/addnewuser")
	public ResponseEntity<Response> createUser(@Valid @RequestBody UserDTO userDTO) {
		log.info("Create User Data : " + userDTO);
		Response response  = userservice.addUser(userDTO);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	//Updates an existing user data
	@PutMapping("/updateuser/{token}")
	public ResponseEntity<Response> updateUser(@PathVariable String token,
												  @Valid @RequestBody UserDTO userDTO) {
		log.info("Update User Data : " + userDTO);
		Response response  = userservice.updateUser(token, userDTO);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	//Deletes an existing user data
	@DeleteMapping("/deleteuser")
	public ResponseEntity<Response> deleteUser(@RequestParam String token) {
		log.info("User Data Deleted");
		Response response  = userservice.deleteUser(token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
}
