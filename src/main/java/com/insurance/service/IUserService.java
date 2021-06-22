package com.insurance.service;

import java.util.List;

import com.insurance.dto.UserDTO;
import com.insurance.model.UserRegistrationData;
import com.insurance.util.Response;

public interface IUserService {

	Response addUser(UserDTO userDTO, String access);

	Response updateUser(String token, UserDTO userDTO, String access);

	List<UserRegistrationData> getAllUsers(String token, String access);

	Response deleteUser(String token, String access);

	List<UserRegistrationData> getAllUserBetweenRegisteredDate(String token, String date1, String date2, String access);

	List<UserRegistrationData> getAllUserWithHealthCondition(String token, String healthCondition, String access);

	List<UserRegistrationData> getAllUserWithVehicleData(String token, String vehicleData, String access);

	Response userLogin(String token, String emailId, String password);

	Response forgetPassword(String token, String emailId, String password);

}
