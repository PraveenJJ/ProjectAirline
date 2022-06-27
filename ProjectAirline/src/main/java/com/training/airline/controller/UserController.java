package com.training.airline.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.training.airline.dto.UserDto;
import com.training.airline.dto.ValidateUser;
import com.training.airline.exception.AirlineControllerException;
import com.training.airline.exception.AirlineServiceException;
import com.training.airline.service.UserService;

/**
 * This is a controller class for User. It has all the handler methods related
 * to User Entity. All the requests that are related to User are handled by this
 * controller.
 * 
 * @author Praveen J
 */
@Controller
@ComponentScan(basePackages = "com.airline")
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/airline")
public class UserController {

	/**
	 * This field userService of type UserService is being autowired here in order
	 * to invoke methods from the UserService class.
	 */
	@Autowired
	private UserService userService;

	/**
	 * Logger is instantiated with respect to UserController to log errors
	 * occurring in this class.
	 */
	Logger logger = LoggerFactory.getLogger(UserController.class);

	/**
	 * This method is used to validate the user for the given user id and password,
	 * and return the user dto back to the request as a view, if the validation is
	 * successful.
	 * 
	 * @param validateUser Is obtained from the request body and is passed here
	 * @param model        Is passed here to add the attributes.
	 * @return String with value jsonTemplate to render the view
	 * @throws AirlineControllerException
	 */
	@RequestMapping(value = "/validateUser", method = RequestMethod.POST)
	public String validateUserController(@RequestBody ValidateUser validateUser, Model model)
			throws AirlineControllerException {

		try {

			// The user dto is obtained here.
			UserDto userDto = userService.validateUserService(validateUser);

			// Obtained user dto is added to the model.
			model.addAttribute("user", userDto);

		} catch (AirlineServiceException e) {

			// Any exception that would occur is logged here.
			logger.error("An exception occurred:: " + e.getMessage());

			// After catching AirlineServiceException, an AirlineControllerException is
			// thrown here manually.
			throw new AirlineControllerException("An exception occurred:: " + e.getMessage());

		}

		// returning the view
		return "jsonTemplate";

	}

	/**
	 * This method is used to register the user, and return the user id back to the
	 * request as a view, once the registration is successful.
	 * 
	 * @param userDto Is obtained from the request body and is passed here
	 * @param model   Is passed here to add the attributes.
	 * @return String with value jsonTemplate to render the view
	 * @throws AirlineControllerException
	 */
	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public String registerUserController(@RequestBody UserDto userDto, Model model) throws AirlineControllerException {

		try {

			// The user id is obtained here.
			Integer userId = userService.registerUserService(userDto);

			// Obtained user id is added to the model.
			model.addAttribute("userId", userId);

		} catch (AirlineServiceException e) {

			// Any exception that would occur is logged here.
			logger.error("An exception occurred:: " + e.getMessage());

			// After catching AirlineServiceException, an AirlineControllerException is
			// thrown here manually.
			throw new AirlineControllerException("An exception occurred:: " + e.getMessage());

		}

		// returning the view
		return "jsonTemplate";

	}

}
