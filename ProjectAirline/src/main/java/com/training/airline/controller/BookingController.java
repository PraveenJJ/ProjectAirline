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
import com.training.airline.dto.ValidateUser;
import com.training.airline.exception.AirlineControllerException;
import com.training.airline.exception.AirlineServiceException;
import com.training.airline.service.BookingService;

/**
 * This is a controller class for Booking. It has all the handler methods
 * related to Booking Entity. All the requests that are related to Booking are
 * handled by this controller.
 * 
 * @author Praveen J
 */
@Controller
@ComponentScan(basePackages = "com.airline")
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/airline")
public class BookingController {

	/**
	 * This field bookingService of type BookingService is being autowired here in
	 * order to invoke methods from the BookingService class.
	 */
	@Autowired
	private BookingService bookingService;

	/**
	 * Logger is instantiated with respect to BookingController to log errors
	 * occurring in this class.
	 */
	Logger logger = LoggerFactory.getLogger(BookingController.class);

	/**
	 * This method is used to get the list of bookings made by the given user, and
	 * return the list of bookings back to the request as a view.
	 * 
	 * @param validateUser Is obtained from the request body and is passed here
	 * @param model        Is passed here to add the attributes.
	 * @return String with value jsonTemplate to render the view
	 * @throws AirlineControllerException
	 */
	@RequestMapping(value = "/getBookings", method = RequestMethod.POST)
	public String getBookingForUserController(@RequestBody ValidateUser validateUser, Model model)
			throws AirlineControllerException {

		try {

			// List of booking dtos are obtained.
			List<BookingDto> bookingForUser = bookingService.getBookingForUserService(validateUser);

			// Obtained list of booking dtos are added to the model.
			model.addAttribute("bookings", bookingForUser);

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
	 * This method is used to save the booking made by the user, and return the
	 * booking with updated reference id, back to the request as a view.
	 * 
	 * @param bookingDto Is obtained from the request body and is passed here
	 * @param model      Is passed here to add the attributes.
	 * @return String with value jsonTemplate to render the view
	 * @throws AirlineControllerException
	 */
	@RequestMapping(value = "/saveBooking", method = RequestMethod.POST)
	public String saveBookingController(@RequestBody BookingDto bookingDto, Model model)
			throws AirlineControllerException {

		try {

			// Updated booking dto object is obtained here.
			BookingDto savedBookingDto = bookingService.saveBooking(bookingDto);

			// Obtained booking dto is added to the model.
			model.addAttribute("savedBooking", savedBookingDto);

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
	 * This method is used to cancel the booking made by the user, and return the
	 * booking with updated status, back to the request as a view.
	 * 
	 * @param bookingDto Is obtained from the request body and is passed here
	 * @param model      Is passed here to add the attributes.
	 * @return String with value jsonTemplate to render the view
	 * @throws AirlineControllerException
	 */
	@RequestMapping(value = "/cancelBooking", method = RequestMethod.POST)
	public String cancelBookingController(@RequestBody BookingDto bookingDto, Model model)
			throws AirlineControllerException {

		try {

			// Updated booking dto object is obtained here.
			BookingDto updateBookingDto = bookingService.cancelBookingService(bookingDto);

			// Obtained booking dto object is added to the model.
			model.addAttribute("updateBooking", updateBookingDto);

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
	 * This method is used to get the booking for the given reference id, and return
	 * the booking back to the request as a view.
	 * 
	 * @param bookingDto Is obtained from the request body and is passed here
	 * @param model      Is passed here to add the attributes.
	 * @return String with value jsonTemplate to render the view
	 * @throws AirlineControllerException
	 */
	@RequestMapping(value = "/getBookingById", method = RequestMethod.POST)
	public String getBookingByReferenceIdController(@RequestBody BookingDto bookingDto, Model model)
			throws AirlineControllerException {

		try {

			// Booking dto object is obtained here.
			BookingDto bookingByReferenceId = bookingService.getBookingByReferenceIdService(bookingDto);

			// Obtained booking dto object is added to the model.
			model.addAttribute("bookingById", bookingByReferenceId);

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
