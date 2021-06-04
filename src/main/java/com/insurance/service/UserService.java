package com.insurance.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.dto.UserDTO;
import com.insurance.exception.UserInsuranceException;
import com.insurance.model.UserData;
import com.insurance.repository.UserRepository;
import com.insurance.util.Response;
import com.insurance.util.TokenUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService implements IUserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private TokenUtil tokenUtil;

	//Returns all user data present
	@Override
	public List<UserData> getAllUsers(String token) {
		int id = tokenUtil.decodeToken(token);
		Optional<UserData> isPresent = userRepository.findById(id);
		if(isPresent.isPresent()) {
			log.info("Get All User Data");
			List<UserData> getAllUsers = userRepository.findAll();
			return getAllUsers;
		}else {
			log.error("User Token Is Not valid");
			throw new UserInsuranceException(400, "User Token Is Not Valid");
		}	
	}

	//Adds a new user data
	@Override
	public Response addUser(UserDTO userDTO) {
		Optional<UserData> isPresent = userRepository.findByMobileNo(userDTO.getMobileNo());
		if(isPresent.isPresent()) {
			log.error("User Already Added");
			throw new UserInsuranceException(400, "User Already Added");
		}else {
			log.info("Add User : " + userDTO);
			UserData user = modelmapper.map(userDTO, UserData.class);
			userRepository.save(user);
			String token = tokenUtil.createToken(user.getUserId());
			return new Response(200, "User Data Added Successfully", token);
		}	
	}

	//Updates an existing user data
	@Override
	public Response updateUser(String token, UserDTO userDTO) {
		
		int id = tokenUtil.decodeToken(token);
		Optional<UserData> isPresent = userRepository.findById(id);
		if(isPresent.isPresent()) {
			log.info("Update User : " + userDTO);
			isPresent.get().updateUser(userDTO);
			userRepository.save(isPresent.get());
			return new Response(200, "User Data Updated Successfully", token);
		}else {
			log.error("User Doesnt Exist");
			throw new UserInsuranceException(400, "User Doesnt Exist");
		}	
	}

	//Deletes an existing user data
	@Override
	public Response deleteUser(String token) {
		int id = tokenUtil.decodeToken(token);
		Optional<UserData> isPresent = userRepository.findById(id);
		if(isPresent.isPresent()) {
			log.info("User Data Deleted");
			userRepository.delete(isPresent.get());
			return new Response(200, "User Data Deleted Successfully", token);
		}else {
			log.error("User Token Is Not Valid");
			throw new UserInsuranceException(400, "User Token Is Not Valid");
		}
	}
}
