package com.training.airline.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.training.airline.exception.AirlineControllerException;

/**
 * This is a class to handle responses for all the AirlineControllerExceptions
 * that is being thrown.
 * 
 * @author 251656
 */
@ControllerAdvice
public class AirlineControllerExceptionResponse {

	/**
	 * For any AirlineControllerException that occurs in the application, this
	 * method will be invoked to give a response of 404 not found along with the
	 * message that is returned in this method.
	 * 
	 * @param airlineControllerException The AirlineControllerException object is
	 *                                   passed here.
	 * @return String message of the exception
	 */
	@ResponseBody
	@ExceptionHandler(AirlineControllerException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String productNotFoundHandler(AirlineControllerException airlineControllerException) {

//		return "{\"status\": 404}";
		return airlineControllerException.getMessage();

	}

}
