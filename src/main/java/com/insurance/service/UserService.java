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

@Service
public class UserService implements IUserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private TokenUtil tokenUtil;

	@Override
	public List<UserData> getAllUsers(String token) {
		int id = tokenUtil.decodeToken(token);
		Optional<UserData> isPresent = userRepository.findById(id);
		if(isPresent.isPresent()) {
			List<UserData> getAllUsers = userRepository.findAll();
			return getAllUsers;
		}else {
			throw new UserInsuranceException(400, "Token is not Valid");
		}	
	}

	@Override
	public Response addUser(UserDTO userDTO) {
		Optional<UserData> isPresent = userRepository.findByMobileNo(userDTO.getMobileNo());
		if(isPresent.isPresent()) {
			throw new UserInsuranceException(400, "Contact Already Added");
		}else {
			UserData user = modelmapper.map(userDTO, UserData.class);
			userRepository.save(user);
			String token = tokenUtil.createToken(user.getUserId());
			return new Response(200, "Contact Added Successfully", token);
		}	
	}

	@Override
	public Response updateUser(String token, UserDTO userDTO) {
		
		int id = tokenUtil.decodeToken(token);
		Optional<UserData> isPresent = userRepository.findById(id);
		if(isPresent.isPresent()) {
			isPresent.get().updateUser(userDTO);
			userRepository.save(isPresent.get());
			return new Response(200, "Contact Updated Successfully", token);
		}else {
			throw new UserInsuranceException(400, "Contact Doesnt Exist");
		}	
	}

	@Override
	public Response deleteUser(String token) {
		int id = tokenUtil.decodeToken(token);
		Optional<UserData> isPresent = userRepository.findById(id);
		if(isPresent.isPresent()) {
			userRepository.delete(isPresent.get());
			return new Response(200, "Contact Deleted Successfully", token);
		}else {
			throw new UserInsuranceException(400, "Token is not Valid");
		}
	}
}
