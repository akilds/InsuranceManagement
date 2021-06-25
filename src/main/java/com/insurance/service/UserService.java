package com.insurance.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.dto.UserDTO;
import com.insurance.exception.UserInsuranceException;
import com.insurance.model.UserRegistrationData;
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
	
	private enum ACCESS{
		admin, user;
	}

	//Returns all user data present
	@Override
	public List<UserRegistrationData> getAllUsers(String token, String access) {
		int id = tokenUtil.decodeToken(token);
		Optional<UserRegistrationData> isPresent = userRepository.findById(id);
		if(access.equals(ACCESS.admin.name())) {
			if(isPresent.isPresent()) {
				log.info("Get All User Data");
				List<UserRegistrationData> getAllUsers = userRepository.findAll();
				return getAllUsers;
			}else {
				log.error("User Token Is Not valid");
				throw new UserInsuranceException(400, "User Token Is Not Valid");
			}
		}else {
			log.error("Not Authorised");
			throw new UserInsuranceException(400, "Not Authorised");
		}	
	}

	//Returns all user data between dates
	@Override
	public List<UserRegistrationData> getAllUserBetweenRegisteredDate(String token, String date1,
														  String date2, String access) {
		int id = tokenUtil.decodeToken(token);
		Optional<UserRegistrationData> isPresent = userRepository.findById(id);
		if(access.equals(ACCESS.admin.name())) {
			if(isPresent.isPresent()) {
				log.info("Get All User Data Between Dates");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); 
				LocalDateTime start = LocalDateTime.parse(date1, formatter);
				LocalDateTime end = LocalDateTime.parse(date2, formatter);
				List<UserRegistrationData> getAllUsers = userRepository.findAllUserBetweenDates(start,end);
				return getAllUsers;
			}else {
				log.error("User Token Is Not valid");
				throw new UserInsuranceException(400, "User Token Is Not Valid");
			}
		}else {
			log.error("Not Authorised");
			throw new UserInsuranceException(400, "Not Authorised");
		}	
	}
	
	//Returns all user data based on health condition
	@Override
	public List<UserRegistrationData> getAllUserWithHealthCondition(String token, String healthCondition, String access) {
		int id = tokenUtil.decodeToken(token);
		Optional<UserRegistrationData> isPresent = userRepository.findById(id);
		if(access.equals(ACCESS.admin.name())) {
			if(isPresent.isPresent()) {
				log.info("Get All User DataWith Health Condition");
				List<UserRegistrationData> getAllUsers = userRepository.findAllByHealthCondition(healthCondition);
				return getAllUsers;
			}else {
				log.error("User Token Is Not valid");
				throw new UserInsuranceException(400, "User Token Is Not Valid");
			}
		}else {
			log.error("Not Authorised");
			throw new UserInsuranceException(400, "Not Authorised");
		}	
	}
	
	//Returns all user data based on vehicle data
	@Override
	public List<UserRegistrationData> getAllUserWithVehicleData(String token, String vehicleData, String access) {
		int id = tokenUtil.decodeToken(token);
		Optional<UserRegistrationData> isPresent = userRepository.findById(id);
		if(access.equals(ACCESS.admin.name())) {
			if(isPresent.isPresent()) {
				log.info("Get All User Data Based On Vehicle Data");
				List<UserRegistrationData> getAllUsers = userRepository.findAllByVehicleData(vehicleData);
				return getAllUsers;
			}else {
				log.error("User Token Is Not valid");
				throw new UserInsuranceException(400, "User Token Is Not Valid");
			}
		}else {
			log.error("Not Authorised");
			throw new UserInsuranceException(400, "Not Authorised");
		}	
	}
		
	//Adds a new user data
	@Override
	public Response addUser(UserDTO userDTO, String access) {
		Optional<UserRegistrationData> isPresent = userRepository.findByMobileNo(userDTO.getMobileNo());
		if(access.equals(ACCESS.admin.name())) {
			if(isPresent.isPresent()) {
				log.error("User Already Added");
				throw new UserInsuranceException(400, "User Already Added");
			}else {
				log.info("Add User : " + userDTO);
				UserRegistrationData user = modelmapper.map(userDTO, UserRegistrationData.class);
				userRepository.save(user);
				String token = tokenUtil.createToken(user.getUserId());
				return new Response(200, "User Data Added Successfully", token);
			}	
		}else {
			log.error("Not Authorised");
			throw new UserInsuranceException(400, "Not Authorised");
		}	
	}

	//Updates an existing user data
	@Override
	public Response updateUser(String token, UserDTO userDTO, String access) {
		
		int id = tokenUtil.decodeToken(token);
		Optional<UserRegistrationData> isPresent = userRepository.findById(id);
		if(access.equals(ACCESS.user.name())) {
			if(isPresent.isPresent()) {
				log.info("Update User : " + userDTO);
				isPresent.get().updateUser(userDTO);
				userRepository.save(isPresent.get());
				return new Response(200, "User Data Updated Successfully", token);
			}else {
				log.error("User Doesnt Exist");
				throw new UserInsuranceException(400, "User Doesnt Exist");
			}
		}else {
			log.error("Not Authorised");
			throw new UserInsuranceException(400, "Not Authorised");
		}	
	}

	//User Login
	@Override
	public Response userLogin(String token, String emailId, String password) {
		int id = tokenUtil.decodeToken(token);
		Optional<UserRegistrationData> isPresent = userRepository.findById(id);
		if(isPresent.isPresent()) {
			if(isPresent.get().getEmailId().equals(emailId)&&isPresent.get().getPassword().equals(password)) {
				return new Response(200, "User Logged In Successfully", token);
			}else {
				log.error("Incorrect EmailId or Password");
				throw new UserInsuranceException(400, "Incorrect EmailId or Password");
			}
		}else {
			log.error("User Doesnt Exist");
			throw new UserInsuranceException(400, "User Doesnt Exist");
		}	
	}
		
	//Change Password
	@Override
	public Response forgetPassword(String token, String emailId, String password) {
		int id = tokenUtil.decodeToken(token);
		Optional<UserRegistrationData> isPresent = userRepository.findById(id);
		if(isPresent.isPresent()) {
			if(isPresent.get().getEmailId().equals(emailId)) {
				isPresent.get().setPassword(password);
				userRepository.save(isPresent.get());
				return new Response(200, "Password Changed Successfully", token);
			}else {
				log.error("InValid EmailId");
				throw new UserInsuranceException(400, "InValid EmailId");
			}
		}else {
			log.error("User Doesnt Exist");
			throw new UserInsuranceException(400, "User Doesnt Exist");
		}	
	}
		
	//Deletes an existing user data
	@Override
	public Response deleteUser(String token, String access) {
		int id = tokenUtil.decodeToken(token);
		Optional<UserRegistrationData> isPresent = userRepository.findById(id);
		if(access.equals(ACCESS.user.name())) {
			if(isPresent.isPresent()) {
				log.info("User Data Deleted");
				userRepository.delete(isPresent.get());
				return new Response(200, "User Data Deleted Successfully", token);
			}else {
				log.error("User Token Is Not Valid");
				throw new UserInsuranceException(400, "User Token Is Not Valid");
			}
		}else {
			log.error("Not Authorised");
			throw new UserInsuranceException(400, "Not Authorised");
		}
	}
}
