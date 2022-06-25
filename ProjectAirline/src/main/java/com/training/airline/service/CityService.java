package com.training.airline.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.training.airline.dto.CityDto;
import com.training.airline.exception.AirlineServiceException;
import com.training.airline.model.City;
import com.training.airline.repository.CityRepository;
import com.training.airline.utility.DtoConverter;

/**
 * This is a service class for City. It has all the service methods related to
 * City Entity. All the services that are related to city are handled by this
 * service class.
 * 
 * @author 251656
 */
@Service
@Transactional
public class CityService {

	/**
	 * This field cityRepository of type CityRepository is being autowired here in
	 * order to invoke methods from the CityRepository interface.
	 */
	@Autowired
	private CityRepository cityRepository;

	/**
	 * Logger is instantiated with respect to CityService to log errors occurring in
	 * this class.
	 */
	Logger logger = LoggerFactory.getLogger(CityService.class);

	/**
	 * This method is used to get the list of cities, and return it back to the
	 * controller.
	 * 
	 * @return List of city dtos
	 * @throws AirlineServiceException
	 */
	public List<CityDto> getAllCitiesService() throws AirlineServiceException {

		// Reference for list of city dto
		List<CityDto> cityDtoList = null;

		try {

			// Calling repository method to find all cities
			List<City> cities = cityRepository.findAll();

			// creating array list object
			cityDtoList = new ArrayList<>();

			// iterating over the list of cities
			for (Iterator<City> iterator = cities.iterator(); iterator.hasNext();) {

				// city object from the list
				City city = iterator.next();

				// converting the city object into city dto object
				CityDto cityDto = DtoConverter.cityToCityDto(city);

				// adding the city dto object to the list of city dto
				cityDtoList.add(cityDto);

			}

		} catch (Exception e) {

			// Any exception that would occur is logged here.
			logger.error("An exception has occurred while getting all the cities... " + e.getMessage());

			// After catching Exception, an AirlineServiceException is
			// thrown here manually.
			throw new AirlineServiceException(
					"An exception has occurred while getting all the cities... " + e.getMessage());

		}

		// return the list of city dtos
		return cityDtoList;

	}

}
