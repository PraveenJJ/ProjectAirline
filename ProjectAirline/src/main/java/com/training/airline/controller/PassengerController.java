package com.training.airline.controller;

import java.util.List;

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

import com.training.airline.dto.BookingDto;
import com.training.airline.dto.CancelPassengerDto;
import com.training.airline.dto.PassengerDto;
import com.training.airline.exception.AirlineControllerException;
import com.training.airline.exception.AirlineServiceException;
import com.training.airline.service.PassengerService;

/**
 * This is a controller class for Passenger. It has all the handler methods
 * related to Passenger Entity. All the requests that are related to Passenger
 * are handled by this controller.
 * 
 * @author Praveen J
 */
@Controller
@ComponentScan(basePackages = "com.airline")
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/airline")
public class PassengerController {

	/**
	 * This field passengerService of type PassengerService is being autowired here
	 * in order to invoke methods from the PassengerService class.
	 */
	@Autowired
	private PassengerService passengerService;

	/**
	 * Logger is instantiated with respect to PassengerController to log errors
	 * occurring in this class.
	 */
	Logger logger = LoggerFactory.getLogger(PassengerController.class);

	/**
	 * This method is used to get the list of passengers present in the given
	 * booking reference id, and return the list of passengers back to the request
	 * as a view.
	 * 
	 * @param bookingDto Is obtained from the request body and is passed here
	 * @param model      Is passed here to add the attributes.
	 * @return String with value jsonTemplate to render the view
	 * @throws AirlineControllerException
	 */
	@RequestMapping(value = "/getPassengers", method = RequestMethod.POST)
	public String getBookingPassengersController(@RequestBody BookingDto bookingDto, Model model)
			throws AirlineControllerException {

		try {

			// List of passengers is obtained here.
			List<PassengerDto> passengersList = passengerService.getBookingPassengersService(bookingDto);

			// Obtained list of passengers are added to the model.
			model.addAttribute("passengersList", passengersList);

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
	 * This method is used to get the cancel passenger tickets present in the given
	 * booking reference id, and return the updated booking back to the request as a
	 * view.
	 * 
	 * @param cancelPassengerDto Is obtained from the request body and is passed
	 *                           here
	 * @param model              Is passed here to add the attributes.
	 * @return String with value jsonTemplate to render the view
	 * @throws AirlineControllerException
	 */
	@RequestMapping(value = "/cancelPassengerByTicket", method = RequestMethod.POST)
	public String cancelPassengerByTicketController(@RequestBody CancelPassengerDto cancelPassengerDto, Model model)
			throws AirlineControllerException {

		try {

			// Updated booking dto object is obtained here.
			BookingDto updatedBookingDto = passengerService.cancelPassengerByTicketService(cancelPassengerDto);

			// Obtained booking dto object is added to the model.
			model.addAttribute("updatedBooking", updatedBookingDto);

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
