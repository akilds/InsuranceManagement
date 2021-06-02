package com.insurance.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.insurance.util.Response;


public class UserExceptionHandler {

private static final String message = "Exception while processing REST Request";
	
	private static final int errorCode = 400;
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Response> handleMethodArgumentNotValidException(
										MethodArgumentNotValidException exception) {
		List<ObjectError> errorList = exception.getBindingResult().getAllErrors();
		List<String> errorMsg = errorList.stream()
								.map(objErr -> objErr.getDefaultMessage())
								.collect(Collectors.toList());
		Response response = new Response(errorCode, message, errorMsg);
		return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserInsuranceException.class)
	public ResponseEntity<Response> handleContactRegisterException(
										UserInsuranceException exception) {
		Response response = new Response(exception.getStatusCode(), message, exception.getStatusMessage());
		return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
	}
}
