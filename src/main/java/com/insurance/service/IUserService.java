package com.insurance.service;

import java.util.List;

import com.insurance.dto.UserDTO;
import com.insurance.model.UserData;
import com.insurance.util.Response;

public interface IUserService {

	Response addUser(UserDTO userDTO);

	Response updateUser(String token, UserDTO userDTO);

	List<UserData> getAllUsers(String token);

	Response deleteUser(String token);

	List<UserData> getAllUserBetweenRegisteredDate(String token, String date1, String date2);

	List<UserData> getAllUserWithHealthCondition(String token, String healthCondition);

	List<UserData> getAllUserWithVehicleData(String token, String vehicleData);

}
