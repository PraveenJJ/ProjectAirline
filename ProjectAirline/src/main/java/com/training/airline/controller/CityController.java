package com.training.airline.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.training.airline.dto.CityDto;
import com.training.airline.exception.AirlineControllerException;
import com.training.airline.exception.AirlineServiceException;
import com.training.airline.service.CityService;

/**
 * This is a controller class for City. It has all the handler methods related
 * to City Entity. All the requests that are related to city are handled by this
 * controller.
 * 
 * @author Praveen J
 */
@Controller
@ComponentScan(basePackages = "com.airline")
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/airline")
public class CityController {

	/**
	 * This field cityService of type CityService is being autowired here in order
	 * to invoke methods from the CityService class.
	 */
	@Autowired
	private CityService cityService;

	/**
	 * Logger is instantiated with respect to CityController to log errors occurring
	 * in this class.
	 */
	Logger logger = LoggerFactory.getLogger(CityController.class);

	/**
	 * This method is used to get the list of cities, and return it back to the
	 * request as a view.
	 * 
	 * @param model Is passed here to add the attributes.
	 * @return String with value jsonTemplate to render the view
	 * @throws AirlineControllerException
	 */
	@RequestMapping(value = "/allCities", method = RequestMethod.GET)
	public String getAllCitiesController(Model model) throws AirlineControllerException {

		try {

			// List of city dtos are obtained
			List<CityDto> cityDtoList = cityService.getAllCitiesService();

			// Obtained city dto list is added to the model
			model.addAttribute("cityList", cityDtoList);

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
