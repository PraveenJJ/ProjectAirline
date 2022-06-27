package com.training.airline.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.training.airline.dto.FlightDto;
import com.training.airline.dto.FlightsByRouteDto;
import com.training.airline.exception.AirlineServiceException;
import com.training.airline.model.City;
import com.training.airline.model.Flight;
import com.training.airline.repository.CityRepository;
import com.training.airline.repository.FlightRepository;
import com.training.airline.utility.DtoConverter;
import com.training.airline.utility.Validation;

/**
 * This is a service class for Flight. It has all the service methods related to
 * Flight Entity. All the services that are related to flights are handled by
 * this service class.
 * 
 * @author Praveen J
 */
@Service
@Transactional
public class FlightService {

	/**
	 * This field flightRepository of type FlightRepository is being autowired here
	 * in order to invoke methods from the FlightRepository interface.
	 */
	@Autowired
	private FlightRepository flightRepository;

	/**
	 * This field cityRepository of type CityRepository is being autowired here in
	 * order to invoke methods from the CityRepository interface.
	 */
	@Autowired
	private CityRepository cityRepository;

	/**
	 * Logger is instantiated with respect to FlightService to log errors occurring
	 * in this class.
	 */
	Logger logger = LoggerFactory.getLogger(FlightService.class);

	/**
	 * This method is used to get the list of flights between given origin city and
	 * destination city, and return it back to the controller.
	 * 
	 * @param flightsByRouteDto
	 * @return List of flight dtos
	 * @throws AirlineServiceException
	 */
	public List<FlightDto> getFlightsByRouteService(FlightsByRouteDto flightsByRouteDto)
			throws AirlineServiceException {

		// getting the origin city name
		String originCityName = flightsByRouteDto.getOriginCity(); 
		
		// if the validation of origin city name fails, if block gets executed
		if (!(Validation.stringValidation(originCityName))) {
			
			// Any exception that would occur is logged here.
			logger.error("Origin city name is missing...");

			// Manually throwing new AirlineServiceException since origin city name is missing
			throw new AirlineServiceException(
					"Origin city name is missing...");
			
		}

		// getting the destination city name
		String destinationCityName = flightsByRouteDto.getDestinationCity(); 
		
		// if the validation of destination city name fails, if block gets executed
		if (!(Validation.stringValidation(destinationCityName))) {
			
			// Any exception that would occur is logged here.
			logger.error("Destination city name is missing...");

			// Manually throwing new AirlineServiceException since destination city name is missing
			throw new AirlineServiceException(
					"Destination city name is missing...");
			
		}

		// instantiating new Arraylist for flight dto list
		List<FlightDto> flightDtoList = new ArrayList<>();

		try {

			// getting the origin city object by using the city name
			City originCityOp = cityRepository.findByCityName(originCityName);

			// getting the destination city object by using the city name
			City destinationCityOp = cityRepository.findByCityName(destinationCityName);

			// getting the list of flights between the given origin city and destination
			// city
			List<Flight> flightList = flightRepository.getFlightsByRouteRepository(originCityOp.getCityId(),
					destinationCityOp.getCityId());

			if (flightList.isEmpty()) {

				// Any exception that would occur is logged here.
				logger.error("There are no flights available for the given origin and destination...");

				// Manually throwing new AirlineServiceException since flights are not available
				throw new AirlineServiceException(
						"There are no flights available for the given origin and destination...");

			}

			// iterating over the flight list
			for (Iterator<Flight> iterator = flightList.iterator(); iterator.hasNext();) {

				// each flight object from the list
				Flight flight = iterator.next();

				// flight object is converted to flight dto object
				FlightDto flightDto = DtoConverter.flightToFlightDto(flight);

				// flight dto object is added to the flight dto list
				flightDtoList.add(flightDto);

			}

		} catch (SQLException e) {

			// Any exception that would occur is logged here.
			logger.error("An exception has occurred while getting flights by route... " + e.getMessage());

			// After catching SQLException, an AirlineServiceException is
			// thrown here manually.
			throw new AirlineServiceException(
					"An exception has occurred while getting flights by route... " + e.getMessage());

		} catch (Exception e) {

			// Any exception that would occur is logged here.
			logger.error("An exception has occurred while getting flights by route... " + e.getMessage());

			// After catching Exception, an AirlineServiceException is
			// thrown here manually.
			throw new AirlineServiceException(
					"An exception has occurred while getting flights by route... " + e.getMessage());

		}
 
		// returning the list of flight dtos
		return flightDtoList;

	}

}
