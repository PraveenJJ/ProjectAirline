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

import com.training.airline.dto.FlightAvailDto;
import com.training.airline.dto.FlightsAvailableDateRangeDto;
import com.training.airline.dto.FlightsAvailableOnDateDto;
import com.training.airline.exception.AirlineControllerException;
import com.training.airline.exception.AirlineServiceException;
import com.training.airline.service.FlightAvailService;

/**
 * This is a controller class for FlightAvail. It has all the handler methods
 * related to FlightAvail Entity. All the requests that are related to
 * flightAvail are handled by this controller.
 * 
 * @author Praveen J
 */
@Controller
@ComponentScan(basePackages = "com.airline")
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/airline")
public class FlightAvailController {

	/**
	 * This field flightAvailService of type FlightAvailService is being autowired
	 * here in order to invoke methods from the FlightAvailService class.
	 */
	@Autowired
	private FlightAvailService flightAvailService;

	/**
	 * Logger is instantiated with respect to FlightAvailController to log errors
	 * occurring in this class.
	 */
	Logger logger = LoggerFactory.getLogger(FlightAvailController.class);

	/**
	 * This method is used to get the list of flights available for the given flight
	 * id on a particular date, and return it back to the request as a view.
	 * 
	 * @param flightsAvailableOnDateDto Is obtained from the request body and is
	 *                                  passed here
	 * @param model                     Is passed here to add the attributes.
	 * @return String with value jsonTemplate to render the view
	 * @throws AirlineControllerException
	 */
	@RequestMapping(value = "/flightOnDate", method = RequestMethod.POST)
	public String getFlightOnDateController(@RequestBody FlightsAvailableOnDateDto flightsAvailableOnDateDto,
			Model model) throws AirlineControllerException {

		try {

			// List of flight avail dtos are obtained here
			FlightAvailDto flightAvailDto = flightAvailService.getFlightOnDateService(flightsAvailableOnDateDto);

			// Obtained list of flight avail dtos are added to the model
			model.addAttribute("flightAvailable", flightAvailDto);

		} catch (AirlineServiceException e) {

			// Any exception that would occur is logged here.
			logger.error("An exception occurred:: " + e.getMessage());

			// After catching AirlineServiceException, an AirlineControllerException is
			// thrown here manually.
			throw new AirlineControllerException("An exception occurred..." + e.getMessage());

		}

		// returning the view
		return "jsonTemplate";

	}

	/**
	 * This method is used to get the list of flights available for the given origin
	 * city and destination city on a particular date, and return it back to the
	 * request as a view.
	 * 
	 * @param flightsAvailableOnDateDto Is obtained from the request body and is
	 *                                  passed here
	 * @param model                     Is passed here to add the attributes.
	 * @return String with value jsonTemplate to render the view
	 * @throws AirlineControllerException
	 */
	@RequestMapping(value = "/flightsBetweenCityOnDate", method = RequestMethod.POST)
	public String getFlightsBetweenCityOnDate(@RequestBody FlightsAvailableOnDateDto flightsAvailableOnDateDto,
			Model model) throws AirlineControllerException {

		try {

			// List of flights available dtos are obtained.
			List<FlightAvailDto> flightsBetweenCityOnDate = flightAvailService
					.getFlightsBetweenCityOnDate(flightsAvailableOnDateDto);

			// Obtained list of flights available dtos are added to the model.
			model.addAttribute("flightAvailableOnDate", flightsBetweenCityOnDate);

		} catch (AirlineServiceException e) {

			// Any exception that would occur is logged here.
			logger.error("An exception occurred:: " + e.getMessage());

			// After catching AirlineServiceException, an AirlineControllerException is
			// thrown here manually.
			throw new AirlineControllerException("An exception occurred..." + e.getMessage());

		}

		// returning the view
		return "jsonTemplate";

	}

	/**
	 * This method is used to get the list of flights available for the given origin
	 * city and destination city within a given date range, and return it back to
	 * the request as a view.
	 * 
	 * @param flightsAvailableDateRangeDto Is obtained from the request body and is
	 *                                     passed here
	 * @param model                        Is passed here to add the attributes.
	 * @return String with value jsonTemplate to render the view
	 * @throws AirlineControllerException
	 */
	@RequestMapping(value = "/flightsBetweenCityDateRange", method = RequestMethod.POST)
	public String getFlightsBetweenDateRangeForCitiesController(
			@RequestBody FlightsAvailableDateRangeDto flightsAvailableDateRangeDto, Model model)
			throws AirlineControllerException {

		try {

			// List of flight available dtos are obtained here.
			List<FlightAvailDto> flightsBetweenDateRange = flightAvailService
					.getFlightsBetweenDateRangeForCitiesService(flightsAvailableDateRangeDto);

			// Obtained list of flight available dtos are added to the model.
			model.addAttribute("flightAvailableDateRange", flightsBetweenDateRange);

		} catch (AirlineServiceException e) {

			// Any exception that would occur is logged here.
			logger.error("An exception occurred:: " + e.getMessage());

			// After catching AirlineServiceException, an AirlineControllerException is
			// thrown here manually.
			throw new AirlineControllerException("An exception occurred..." + e.getMessage());

		}

		// returning the view
		return "jsonTemplate";

	}

}
