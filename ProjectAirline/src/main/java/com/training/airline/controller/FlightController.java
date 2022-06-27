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

import com.training.airline.dto.FlightDto;
import com.training.airline.dto.FlightsByRouteDto;
import com.training.airline.exception.AirlineControllerException;
import com.training.airline.exception.AirlineServiceException;
import com.training.airline.service.FlightService;

/**
 * This is a controller class for Flight. It has all the handler methods related
 * to Flight Entity. All the requests that are related to flight are handled by
 * this controller.
 * 
 * @author Praveen J
 */
@Controller
@ComponentScan(basePackages = "com.airline")
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/airline")
public class FlightController {

	/**
	 * This field flightService of type FlightService is being autowired here in
	 * order to invoke methods from the FlightService class.
	 */
	@Autowired
	private FlightService flightService;

	/**
	 * Logger is instantiated with respect to FlightController to log errors
	 * occurring in this class.
	 */
	Logger logger = LoggerFactory.getLogger(FlightController.class);

	/**
	 * This method is used to get the list of flights for the given origin city and
	 * destination city, and return it back to the request as a view.
	 * 
	 * @param flightsByRouteDto Is obtained from the request body and is passed here
	 * @param model             Is passed here to add the attributes.
	 * @return String with value jsonTemplate to render the view
	 * @throws AirlineControllerException
	 */
	@RequestMapping(value = "/flightsByRoute", method = RequestMethod.POST)
	public String getFlightsByRouteController(@RequestBody FlightsByRouteDto flightsByRouteDto, Model model)
			throws AirlineControllerException {

		try {

			// List of flight dtos are obtained
			List<FlightDto> flightsByRoute = flightService.getFlightsByRouteService(flightsByRouteDto);

			// Obtained list of flight dtos are added to the model
			model.addAttribute("flightsByRoute", flightsByRoute);

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
