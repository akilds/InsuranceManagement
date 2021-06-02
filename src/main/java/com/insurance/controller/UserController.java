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

@RestController
@RequestMapping("/useri")
public class UserController {

	@Autowired
	private IUserService userservice;
	
	@GetMapping("/getallusers/{token}")
	public ResponseEntity<List<?>> getAllUsers(@PathVariable String token) {
		List<UserData> response = userservice.getAllUsers(token);
		return new ResponseEntity<List<?>>(response, HttpStatus.OK);
	}
	
	@PostMapping("/addnewuser")
	public ResponseEntity<Response> createUser(@Valid @RequestBody UserDTO userDTO) {
		Response response  = userservice.addUser(userDTO);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@PutMapping("/updateuser/{token}")
	public ResponseEntity<Response> updateUser(@PathVariable String token,
												  @Valid @RequestBody UserDTO userDTO) {
		Response response  = userservice.updateUser(token, userDTO);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/deletecontact")
	public ResponseEntity<Response> deleteContact(@RequestParam String token) {
		Response response  = userservice.deleteUser(token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
}
