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

}
