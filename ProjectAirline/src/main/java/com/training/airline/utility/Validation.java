package com.training.airline.utility;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.training.airline.dto.BookingDto;
import com.training.airline.dto.CancelPassengerDto;
import com.training.airline.dto.PassengerDto;
import com.training.airline.dto.UserDto;
import com.training.airline.exception.ValidationFailureException;

/**
 * This class is a utility class used to have the validation logic. It holds the
 * methods which does the validation for each data input given.
 * 
 * @author 251656
 */
public class Validation {

	/**
	 * Logger is instantiated with respect to CityService to log errors occurring in
	 * this class.
	 */
	private static Logger logger = LoggerFactory.getLogger(Validation.class);

	/**
	 * This method is used to validate string data, to check if it's null or if it's
	 * an empty string.
	 * 
	 * @param data
	 * @return boolean
	 */
	public static boolean stringValidation(String data) {

		// checking if the string is null or an empty string
		if ((data == null) || data.equals("")) {

			// returning false since the validation failed
			return false;

		}

		// returning true since the validation is successful
		return true;

	}

	/**
	 * This method is used to validate integer data, to check if it's null.
	 * 
	 * @param integer
	 * @return boolean
	 */
	public static boolean integerValidation(Integer integer) {

		// checking if the integer is null
		if (integer == null) {

			// returning false since the validation failed.
			return false;

		}

		// returning true since the validation succeeded.
		return true;

	}

	/**
	 * This method is used to validate float data, to check if it's null.
	 * 
	 * @param value
	 * @return boolean
	 */
	public static boolean floatValidation(Float value) {

		// checking if the value is null
		if (value == null) {

			// returning false since the validation failed.
			return false;

		}

		// returning true since the validation succeeded.
		return true;

	}

	/**
	 * This method is used to validate date, to check if it's null or if it's an
	 * empty string
	 * 
	 * @param date
	 * @return boolean
	 */
	public static boolean dateValidation(Date date) {

		// checking if the date is null or an empty string
		if ((date == null) || ((date.toString()).equals(""))) {

			// returning false since the validation failed
			return false;

		}

		// returning true since the validation is successful
		return true;

	}

	/**
	 * This method is used to validate user details.
	 * 
	 * @param userDto
	 * @return boolean
	 * @throws ValidationFailureException
	 */
	public static boolean userValidation(UserDto userDto) throws ValidationFailureException {

		// getting the user name
		String userName = userDto.getName();

		// if the validation of user name fails, if block gets executed
		if (!(Validation.stringValidation(userName))) {

			// Any exception that would occur is logged here.
			logger.error("User name is invalid...");

			// manually throwing ValidationFailureException since user name is invalid
			throw new ValidationFailureException("User name is invalid...");

		}

		// getting the password
		String password = userDto.getPassword();

		// if the validation of password fails, if block gets executed
		if (!(Validation.stringValidation(password))) {

			// Any exception that would occur is logged here.
			logger.error("Password is invalid...");

			// manually throwing ValidationFailureException since password is invalid
			throw new ValidationFailureException("Password is invalid...");

		}

		// getting the credit card number
		String creditCardNumber = userDto.getCreditCardNumber();

		// if the validation of credit card number fails, if block gets executed
		if (!(Validation.stringValidation(creditCardNumber)) || (creditCardNumber.length() != 16)) {

			// Any exception that would occur is logged here.
			logger.error("Credit Card Number is invalid...");

			// manually throwing ValidationFailureException since credit card number is
			// invalid
			throw new ValidationFailureException("Credit Card Number is invalid...");

		}

		// getting the credit card type
		Character creditCardType = userDto.getCreditCardType();

		try {

			// if the validation of credit card type fails, if block gets executed
			if ((creditCardType != 'C')) {

				// Any exception that would occur is logged here.
				logger.error("Credit Card Type is invalid...");

				// manually throwing ValidationFailureException since credit card type is
				// invalid
				throw new ValidationFailureException("Credit Card Type is invalid...");

			}

		} catch (NullPointerException e) {

			// Any exception that would occur is logged here.
			logger.error("Credit Card Type is null...");

			// after catching NullPointerException, manually throwing
			// ValidationFailureException since credit card type is null
			throw new ValidationFailureException("Credit Card Type is null...");

		}

		// getting the credit card month
		Integer creditCardMonth = userDto.getCreditCardMonth();

		// if the validation of credit card month fails, if block gets executed
		if ((creditCardMonth < 1) || (creditCardMonth > 12)) {

			// Any exception that would occur is logged here.
			logger.error("Credit Card Month is invalid...");

			// manually throwing ValidationFailureException since credit card month is
			// invalid
			throw new ValidationFailureException("Credit Card Month is invalid...");

		}

		// getting the credit card year
		Integer creditCardYear = userDto.getCreditCardYear();

		// if the validation of credit card year fails, if block gets executed
		if ((creditCardYear < 2022) || (creditCardYear > 2030)) {

			// Any exception that would occur is logged here.
			logger.error("Credit Card Year is invalid...");

			// manually throwing ValidationFailureException since credit card year is
			// invalid
			throw new ValidationFailureException("Credit Card Year is invalid...");

		}

		// getting the age
		Integer age = userDto.getAge();

		// if the validation of age fails, if block gets executed
		if ((age < 18) || (age > 100)) {

			// Any exception that would occur is logged here.
			logger.error("Age is invalid...");

			// manually throwing ValidationFailureException since age is invalid
			throw new ValidationFailureException("Age is invalid...");

		}

		// returning true since the validation is successful
		return true;
	}

	/**
	 * This method is used to validate the passenger details sent for booking.
	 * 
	 * @param passengerDto
	 * @return boolean
	 * @throws ValidationFailureException
	 */
	public static boolean passengerValidation(PassengerDto passengerDto) throws ValidationFailureException {

		// getting the passenger name
		String passengerName = passengerDto.getPassengerName();

		// if the validation of passenger name fails, if block gets executed
		if (!(Validation.stringValidation(passengerName))) {

			// Any exception that would occur is logged here.
			logger.error("Passenger name is invalid...");

			// manually throwing ValidationFailureException since passenger name is invalid
			throw new ValidationFailureException("Passenger name is invalid...");

		}

		// getting the passenger age
		Integer age = passengerDto.getAge();

		// if the validation of passenger age fails, if block gets executed
		if (!(Validation.integerValidation(age))) {

			// Any exception that would occur is logged here.
			logger.error("Passenger Age is invalid...");

			// manually throwing ValidationFailureException since passenger age is invalid
			throw new ValidationFailureException("Passenger Age is invalid...");

		}

		// getting the status
		String status = passengerDto.getStatus();

		// if the validation of passenger status fails, if block gets executed
		if (!(Validation.stringValidation(status))) {

			// Any exception that would occur is logged here.
			logger.error("Passenger status is invalid...");

			// manually throwing ValidationFailureException since passenger status is
			// invalid
			throw new ValidationFailureException("Passenger status is invalid...");

		}

		// if the status is not equal to B, if block gets executed
		if (!(status.equalsIgnoreCase("B"))) {

			// Any exception that would occur is logged here.
			logger.error("Passenger status is invalid...");

			// manually throwing ValidationFailureException since passenger status is
			// invalid
			throw new ValidationFailureException("Passenger status is invalid...");

		}

		return true;

	}

	/**
	 * This method is used to validate booking details
	 * 
	 * @param bookingDto
	 * @return boolean
	 * @throws ValidationFailureException
	 */
	public static boolean bookingValidation(BookingDto bookingDto) throws ValidationFailureException {

		// getting the user id
		Integer userId = bookingDto.getUser().getUserId();

		// if the validation of user id fails, if block gets executed
		if (!(Validation.integerValidation(userId))) {

			// Any exception that would occur is logged here.
			logger.error("User id is invalid...");

			// manually throwing ValidationFailureException since user id is invalid
			throw new ValidationFailureException("User id is invalid...");

		}

		// getting the flight id
		String flightId = bookingDto.getFlightAvail().getFlight().getFlightId();

		// if the validation of flight id fails, if block gets executed
		if (!(Validation.stringValidation(flightId))) {

			// Any exception that would occur is logged here.
			logger.error("Flight id is invalid...");

			// manually throwing ValidationFailureException since flight id is invalid
			throw new ValidationFailureException("Flight id is invalid...");

		}

		// getting the flight date
		Date flightDate = bookingDto.getFlightAvail().getFlightDate();

		// if the validation of flight date fails, if block gets executed
		if (!(Validation.dateValidation(flightDate))) {

			// Any exception that would occur is logged here.
			logger.error("Flight date is invalid...");

			// manually throwing ValidationFailureException since flight date is invalid
			throw new ValidationFailureException("Flight date is invalid...");

		}

		// getting the status
		String status = bookingDto.getStatus();

		// if the validation of booking status fails, if block gets executed
		if (!(Validation.stringValidation(status))) {

			// Any exception that would occur is logged here.
			logger.error("Booking status is invalid...");

			// manually throwing ValidationFailureException since booking status is invalid
			throw new ValidationFailureException("Booking status is invalid...");

		}

		// if the status is not equal to B, if block gets executed
		if (!(status.equalsIgnoreCase("B"))) {

			// Any exception that would occur is logged here.
			logger.error("Booking status is invalid...");

			// manually throwing ValidationFailureException since booking status is invalid
			throw new ValidationFailureException("Booking status is invalid...");

		}

		// getting the booked seats
		Integer bookedSeats = bookingDto.getBookedSeats();

		// if the validation of booked seats fails, if block gets executed
		if (!(Validation.integerValidation(bookedSeats))) {

			// Any exception that would occur is logged here.
			logger.error("Booked seats is invalid...");

			// manually throwing ValidationFailureException since booked seats is invalid
			throw new ValidationFailureException("Booked seats is invalid...");

		}

		// getting the total cost
		Float totalCost = bookingDto.getTotalCost();

		// if the validation of total cost fails, if block gets executed
		if (!(Validation.floatValidation(totalCost))) {

			// Any exception that would occur is logged here.
			logger.error("Total cost is invalid...");

			// manually throwing ValidationFailureException since total cost is invalid
			throw new ValidationFailureException("Total cost is invalid...");

		}

		// getting the passengers list
		List<PassengerDto> passengers = bookingDto.getPassengers();

		// checking if the passenger list is empty
		if (passengers.isEmpty()) {

			// Any exception that would occur is logged here.
			logger.error("Passenger list is empty...");

			// manually throwing ValidationFailureException since passenger list is empty
			throw new ValidationFailureException("Passenger list is empty...");

		}

		// iterating over the list of passenger dtos
		for (Iterator<PassengerDto> iterator = passengers.iterator(); iterator.hasNext();) {

			// each passenger dto from the list
			PassengerDto passengerDto = iterator.next();

			// validating each passenger
			Validation.passengerValidation(passengerDto);

		}

		// returning true since the validation is successful
		return true;

	}

	/**
	 * This method is used to validate the passenger details sent for cancellation.
	 * 
	 * @param cancelPassengerDto
	 * @return boolean
	 * @throws ValidationFailureException
	 */
	public static boolean cancelPassengerValidation(CancelPassengerDto cancelPassengerDto)
			throws ValidationFailureException {

		// getting the booking reference id
		Integer referenceId = cancelPassengerDto.getReferenceId();

		// if the validation of booking reference id fails, if block gets executed
		if (!(Validation.integerValidation(referenceId))) {

			// Any exception that would occur is logged here.
			logger.error("Booking reference id is invalid...");

			// manually throwing ValidationFailureException since booking reference id is
			// invalid
			throw new ValidationFailureException("Booking reference id is invalid...");

		}

		// getting the list of passenger dtos
		List<PassengerDto> passengerDtos = cancelPassengerDto.getPassengerDtos();

		// iterating over the list of passenger dtos
		for (Iterator<PassengerDto> iterator = passengerDtos.iterator(); iterator.hasNext();) {

			// each passenger dto from the list
			PassengerDto passengerDto = iterator.next();

			Integer ticketNumber = passengerDto.getTicketNumber();

			// if the validation of ticket number id fails, if block gets executed
			if (!(Validation.integerValidation(ticketNumber))) {

				// Any exception that would occur is logged here.
				logger.error("Passenger ticket number is invalid...");

				// manually throwing ValidationFailureException since passenger ticket number is
				// invalid
				throw new ValidationFailureException("Passenger ticket number is invalid...");

			}

		}

		// returning true since the validation is successful
		return true;

	}

}
