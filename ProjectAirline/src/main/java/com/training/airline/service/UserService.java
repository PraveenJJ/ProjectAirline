package com.training.airline.service;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.training.airline.dto.UserDto;
import com.training.airline.dto.ValidateUser;
import com.training.airline.exception.AirlineServiceException;
import com.training.airline.exception.ValidationFailureException;
import com.training.airline.model.User;
import com.training.airline.repository.UserRepository;
import com.training.airline.utility.DtoConverter;
import com.training.airline.utility.Password;
import com.training.airline.utility.Validation;

/**
 * This is a service class for User. It has all the service methods related to
 * User Entity. All the services that are related to users are handled by this
 * service class.
 * 
 * @author 251656
 */
@Service
@Transactional
public class UserService {

	/**
	 * This field userRepository of type UserRepository is being autowired here in
	 * order to invoke methods from the UserRepository interface.
	 */
	@Autowired
	private UserRepository userRepository;

	/**
	 * Logger is instantiated with respect to UserService to log errors occurring in
	 * this class.
	 */
	Logger logger = LoggerFactory.getLogger(UserService.class);

	/**
	 * This method is used to validate the user with the given user id and password
	 * and if validation succeeds, return the user object to the controller.
	 * 
	 * @param validateUser
	 * @return user dto object
	 * @throws AirlineServiceException
	 */
	public UserDto validateUserService(ValidateUser validateUser) throws AirlineServiceException {

		// reference for the user dto object is created here
		UserDto userDto = null;

		try {

			// getting the user id
			Integer userId = validateUser.getUserId();

			// if the validation of user id fails, if block gets executed
			if (!(Validation.integerValidation(userId))) {

				// Any exception that would occur is logged here.
				logger.error("User id is missing...");

				// Manually throw new AirlineServiceException since the user id is not
				// available
				throw new AirlineServiceException("User id is missing...");

			}

			// getting the password
			String password = validateUser.getPassword();

			// if the validation of the password fails, if block gets executed
			if (!(Validation.stringValidation(password))) {

				// Any exception that would occur is logged here.
				logger.error("Password is missing...");

				// Manually throw new AirlineServiceException since the password is not
				// available
				throw new AirlineServiceException("Password is missing...");

			}

			// getting the user by validating the user id and password
			User user = userRepository.validateUserRepository(userId, Password.get_SHA_1_SecurePassword(password));

			// if the validation fails, the user object will be null, in that case, if block
			// gets executed.
			if (user == null) {

				// Any exception that would occur is logged here.
				logger.error("User Not Found for given userId and password");

				// Manually throwing new AirlineServiceException since no user is found
				throw new AirlineServiceException("User Not Found for given userId and password");

			} else {

				// user object is converted into user dto object
				userDto = DtoConverter.userToUserDto(user);

			}

		} catch (SQLException | NoSuchAlgorithmException e) {

			// Any exception that would occur is logged here.
			logger.error("An exception has occurred while validating the user credentials... " + e.getMessage());

			// After catching SQLException or NoSuchAlgorithmException, an
			// AirlineServiceException is thrown here manually.
			throw new AirlineServiceException(
					"An exception has occurred while validating the user credentials... " + e.getMessage());

		} catch (Exception e) {

			// Any exception that would occur is logged here.
			logger.error("An exception has occurred while validating the user credentials... " + e.getMessage());

			// After catching Exception, an AirlineServiceException is
			// thrown here manually.
			throw new AirlineServiceException(
					"An exception has occurred while validating the user credentials... " + e.getMessage());

		}

		// returning the user dto object to the controller.
		return userDto;

	}

	/**
	 * This method is used to register the user with the given details, and once the
	 * registration is successful, the user id for the user is returned back to the
	 * controller.
	 * 
	 * @param userDto
	 * @return user id of type integer
	 * @throws AirlineServiceException
	 */
	public Integer registerUserService(final UserDto userDto) throws AirlineServiceException {

		// reference for user id is created here
		Integer userId = null;

		try {

			// validating the user details
			Validation.userValidation(userDto);

			// if user dto has data then below code will be executed
			if (userDto != null) {

				// getting the user id for the user that is being registered
				String message = userRepository.registerUserRepository(userDto.getName(),
						Password.get_SHA_1_SecurePassword(userDto.getPassword()), userDto.getCreditCardNumber(),
						userDto.getCreditCardType(), userDto.getCreditCardMonth(), userDto.getCreditCardYear(),
						userDto.getAge());

				// if the user registration fails then if block gets executed
				if (message.equalsIgnoreCase("Insertion failed")) {

					// Any exception that would occur is logged here.
					logger.error("User registration failed.");

					// Manually throwing new AirlineServiceException since user registration failed.
					throw new AirlineServiceException("User registration failed.");

				} else {

					// getting the integer format of the user id
					userId = Integer.parseInt(message);

				}

			}

		} catch (SQLException | NoSuchAlgorithmException e) {

			// Any exception that would occur is logged here.
			logger.error("An exception has occurred while registering the user... " + e.getMessage());

			// After catching SQLException or NoSuchAlgorithmException, an
			// AirlineServiceException is thrown here manually.
			throw new AirlineServiceException(
					"An exception has occurred while registering the user... " + e.getMessage());

		} catch (ValidationFailureException e) {

			// Any exception that would occur is logged here.
			logger.error("An exception has occurred while registering the user... " + e.getMessage());

			// After catching ValidationFailureException, an AirlineServiceException is
			// thrown here manually.
			throw new AirlineServiceException(
					"An exception has occurred while registering the user... " + e.getMessage());

		} catch (Exception e) {

			// Any exception that would occur is logged here.
			logger.error("An exception has occurred while registering the user... " + e.getMessage());

			// After catching Exception, an AirlineServiceException is
			// thrown here manually.
			throw new AirlineServiceException(
					"An exception has occurred while registering the user... " + e.getMessage());

		}

		// returning the user id to the controller
		return userId;

	}

}
