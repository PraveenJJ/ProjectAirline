package com.training.airline.service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.training.airline.dto.FlightAvailDto;
import com.training.airline.dto.FlightsAvailableDateRangeDto;
import com.training.airline.dto.FlightsAvailableOnDateDto;
import com.training.airline.exception.AirlineServiceException;
import com.training.airline.model.City;
import com.training.airline.model.Flight;
import com.training.airline.model.FlightAvail;
import com.training.airline.repository.CityRepository;
import com.training.airline.repository.FlightAvailRepository;
import com.training.airline.repository.FlightRepository;
import com.training.airline.utility.DtoConverter;
import com.training.airline.utility.Validation;

/**
 * This is a service class for FlightAvail. It has all the service methods
 * related to FlightAvail Entity. All the services that are related to flights
 * available are handled by this service class.
 * 
 * @author Praveen J
 */
@Service
@Transactional
public class FlightAvailService {

	/**
	 * This field flightAvailRepository of type FlightAvailRepository is being
	 * autowired here in order to invoke methods from the FlightAvailRepository
	 * interface.
	 */
	@Autowired
	private FlightAvailRepository flightAvailRepository;

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
	 * Logger is instantiated with respect to FlightAvailService to log errors
	 * occurring in this class.
	 */
	Logger logger = LoggerFactory.getLogger(FlightAvailService.class);

	/**
	 * This method is used to get the flight available for the given flight id on a
	 * given date, and return it back to the controller.
	 * 
	 * @param flightsAvailableOnDateDto
	 * @return flight avail dto
	 * @throws AirlineServiceException
	 */
	public FlightAvailDto getFlightOnDateService(FlightsAvailableOnDateDto flightsAvailableOnDateDto)
			throws AirlineServiceException {

		// flight avail dto reference is created here
		FlightAvailDto flightAvailDto = null;

		try {

			// getting the flight id from the flight available on date dto object
			String flightId = flightsAvailableOnDateDto.getFlightId();

			// getting the flight date from the flight available on date dto object
			Date travelOn = flightsAvailableOnDateDto.getTravelOn();

			// if the validation of flight id fails, if block gets executed
			if (!(Validation.stringValidation(flightId))) {

				// Any exception that would occur is logged here.
				logger.error("Flight id is missing...");

				// Manually throw new AirlineServiceException since the flight id is not
				// available
				throw new AirlineServiceException("Flight id is missing...");

			}

			// if the validation of flight date fails, if block gets executed
			if (!(Validation.dateValidation(travelOn))) {

				// Any exception that would occur is logged here.
				logger.error("Flight date is missing...");

				// Manually throw new AirlineServiceException since the flight id is not
				// available
				throw new AirlineServiceException("Flight date is missing...");

			}

			// getting the flight for the given flight id on a given date
			FlightAvail flightsAvailable = flightAvailRepository.getFlightsAvailableRepository(flightId, travelOn);

			// if the flight is not available, if block is executed
			if (flightsAvailable == null) {

				// Any exception that would occur is logged here.
				logger.error("There are no flights available for this date...");

				// Manually throw new AirlineServiceException since there are no flights
				// available
				throw new AirlineServiceException("There are no flights available for this date...");

			} else {

				// flight available is converted into flight available dto
				flightAvailDto = DtoConverter.flightAvailToFlightAvailDto(flightsAvailable);

			}

		} catch (SQLException e) {

			// Any exception that would occur is logged here.
			logger.error("An exception has occurred while getting available flights... " + e.getMessage());

			// After catching SQLException, an AirlineServiceException is
			// thrown here manually.
			throw new AirlineServiceException(
					"An exception has occurred while getting available flights... " + e.getMessage());

		} catch (Exception e) {

			// Any exception that would occur is logged here.
			logger.error("An exception has occurred while getting available flights... " + e.getMessage());

			// After catching Exception, an AirlineServiceException is
			// thrown here manually.
			throw new AirlineServiceException(
					"An exception has occurred while getting available flights... " + e.getMessage());

		}

		// flight available dto is returned to the controller
		return flightAvailDto;

	}

	/**
	 * This method is used to get the list of flights available between the given
	 * cities for the given date, and return it back to the controller.
	 * 
	 * @param flightsAvailableOnDateDto
	 * @return List of flight available dtos
	 */
	public List<FlightAvailDto> getFlightsBetweenCityOnDate(FlightsAvailableOnDateDto flightsAvailableOnDateDto) {

		// reference for list of available flights dto is created here
		List<FlightAvailDto> flightAvailDtosList = null;

		try {

			// getting the origin city name
			String originCityName = flightsAvailableOnDateDto.getOriginCity();

			// if the validation of origin city name fails, if block gets executed
			if (!(Validation.stringValidation(originCityName))) {

				// Any exception that would occur is logged here.
				logger.error("Origin city name is missing...");

				// Manually throwing new AirlineServiceException since origin city name is
				// missing
				throw new AirlineServiceException("Origin city name is missing...");

			}

			// getting the destination city name
			String destinationCityName = flightsAvailableOnDateDto.getDestinationCity();

			// if the validation of destination city name fails, if block gets executed
			if (!(Validation.stringValidation(destinationCityName))) {

				// Any exception that would occur is logged here.
				logger.error("Destination city name is missing...");

				// Manually throwing new AirlineServiceException since destination city name is
				// missing
				throw new AirlineServiceException("Destination city name is missing...");

			}

			// getting the flight date from the flight available on date dto object
			Date travelOn = flightsAvailableOnDateDto.getTravelOn();

			// if the validation of flight date fails, if block gets executed
			if (!(Validation.dateValidation(travelOn))) {

				// Any exception that would occur is logged here.
				logger.error("Flight date is missing...");

				// Manually throw new AirlineServiceException since the flight id is not
				// available
				throw new AirlineServiceException("Flight date is missing...");

			}

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
				logger.error("There are no flights between the given cities");

				// Manually throwing new AirlineServiceException since flights are not available
				throw new AirlineServiceException("There are no flights between the given cities");

			}

			// new Arraylist is instantiated here
			flightAvailDtosList = new ArrayList<>();

			// iterating over the flight list
			for (Iterator<Flight> iterator = flightList.iterator(); iterator.hasNext();) {

				// each flight object from the list
				Flight flight = iterator.next();

				// getting the flight for the given flight id on a given date
				FlightAvail flightAvailable = flightAvailRepository.getFlightsAvailableRepository(flight.getFlightId(),
						travelOn);

				// if the flight is available, if block will get executed
				if (flightAvailable != null) {

					// flight available object is converted into flight available dto
					FlightAvailDto flightAvailDto = DtoConverter.flightAvailToFlightAvailDto(flightAvailable);

					// flight available dto added to the list of available flight dtos
					flightAvailDtosList.add(flightAvailDto);

				}

			}

			// if the flight available list is empty, if block gets executed
			if (flightAvailDtosList.isEmpty()) {

				// Any exception that would occur is logged here.
				logger.error("There are no flights between the given cities for the given date");

				// Manually throwing new AirlineServiceException since flights are not available
				throw new AirlineServiceException("There are no flights between the given cities for the given date");

			}

		} catch (SQLException e) {

			// Any exception that would occur is logged here.
			logger.error("An exception has occurred:: " + e.getMessage());

			// After catching SQLException, an AirlineServiceException is
			// thrown here manually.
			throw new AirlineServiceException("An exception has occurred:: " + e.getMessage());

		} catch (Exception e) {

			// Any exception that would occur is logged here.
			logger.error("An exception has occurred:: " + e.getMessage());

			// After catching Exception, an AirlineServiceException is
			// thrown here manually.
			throw new AirlineServiceException("An exception has occurred:: " + e.getMessage());

		}

		// returning the list of available flight dtos to the controller
		return flightAvailDtosList;

	}

	/**
	 * This method is used to get the list of available flight dtos between the
	 * given cities, and between the given date range, and return it back to the
	 * controller.
	 * 
	 * @param flightsAvailableDateRangeDto
	 * @return List of available flight dtos
	 */
	public List<FlightAvailDto> getFlightsBetweenDateRangeForCitiesService(
			FlightsAvailableDateRangeDto flightsAvailableDateRangeDto) {

		// reference for list of available flight dtos is created here.
		List<FlightAvailDto> finalFlightAvailDtosList = null;

		try {

			// getting the origin city name
			String originCityName = flightsAvailableDateRangeDto.getOriginCity();

			// if the validation of origin city name fails, if block gets executed
			if (!(Validation.stringValidation(originCityName))) {

				// Any exception that would occur is logged here.
				logger.error("Origin city name is missing...");

				// Manually throwing new AirlineServiceException since origin city name is
				// missing
				throw new AirlineServiceException("Origin city name is missing...");

			}

			// getting the destination city name
			String destinationCityName = flightsAvailableDateRangeDto.getDestinationCity();

			// if the validation of destination city name fails, if block gets executed
			if (!(Validation.stringValidation(destinationCityName))) {

				// Any exception that would occur is logged here.
				logger.error("Destination city name is missing...");

				// Manually throwing new AirlineServiceException since destination city name is
				// missing
				throw new AirlineServiceException("Destination city name is missing...");

			}

			// getting the flight date from the flight available on date dto object
			Date startDate = flightsAvailableDateRangeDto.getStartDate();

			// if the validation of flight date fails, if block gets executed
			if (!(Validation.dateValidation(startDate))) {

				// Any exception that would occur is logged here.
				logger.error("Flight start date is missing...");

				// Manually throw new AirlineServiceException since the flight id is not
				// available
				throw new AirlineServiceException("Flight start date is missing...");

			}

			// getting the flight date from the flight available on date dto object
			Date endDate = flightsAvailableDateRangeDto.getEndDate();

			// if the validation of flight date fails, if block gets executed
			if (!(Validation.dateValidation(endDate))) {

				// Any exception that would occur is logged here.
				logger.error("Flight end date is missing...");

				// Manually throw new AirlineServiceException since the flight id is not
				// available
				throw new AirlineServiceException("Flight end date is missing...");

			}

			// getting the origin city object by using the city name
			City originCityOp = cityRepository.findByCityName(originCityName);

			// getting the destination city object by using the city name
			City destinationCityOp = cityRepository.findByCityName(destinationCityName);

			// getting the list of flights between the given origin city and destination
			// city
			List<Flight> flightList = flightRepository.getFlightsByRouteRepository(originCityOp.getCityId(),
					destinationCityOp.getCityId());

			// if there are no flights available, if block gets executed
			if (flightList.isEmpty()) {

				// Any exception that would occur is logged here.
				logger.error("There are no flights between the given cities");

				// Manually throwing new AirlineServiceException since flights are not available
				throw new AirlineServiceException("There are no flights between the given cities");

			}

			// new ArrayList is instantiated here
			finalFlightAvailDtosList = new ArrayList<>();

			// iterating over the flight list
			for (Iterator<Flight> iterator = flightList.iterator(); iterator.hasNext();) {

				// each flight object from the list
				Flight flight = iterator.next();

				// getting the list of available flights for the given flight id between the
				// given date range
				List<FlightAvail> flightAvailableList = flightAvailRepository
						.getFlightsAvailableOnDateRangeRepository(flight.getFlightId(), startDate, endDate);

				// if flights are available, if block gets executed.
				if (!flightAvailableList.isEmpty()) {

					// list of flight available is converted into list of flight available dtos
					List<FlightAvailDto> flightAvailDtoList = DtoConverter
							.flightAvailListToFlightAvailListDto(flightAvailableList);

					// adding the list of flight available dtos to the final list of flight
					// available dtos
					finalFlightAvailDtosList.addAll(flightAvailDtoList);

				}

			}

			// if the final list of flight available dtos is empty, if block gets executed
			if (finalFlightAvailDtosList.isEmpty()) {

				// Any exception that would occur is logged here.
				logger.error("There are no flights between the given cities for the given date range");

				// Manually throwing new AirlineServiceException since flights are not available
				throw new AirlineServiceException(
						"There are no flights between the given cities for the given date range");

			}

		} catch (SQLException e) {

			// Any exception that would occur is logged here.
			logger.error("An exception has occurred:: " + e.getMessage());

			// After catching SQLException, an AirlineServiceException is
			// thrown here manually.
			throw new AirlineServiceException("An exception has occurred:: " + e.getMessage());

		} catch (Exception e) {

			// Any exception that would occur is logged here.
			logger.error("An exception has occurred:: " + e.getMessage());

			// After catching Exception, an AirlineServiceException is
			// thrown here manually.
			throw new AirlineServiceException("An exception has occurred:: " + e.getMessage());

		}

		// returning the final list of flight available dtos to the controller
		return finalFlightAvailDtosList;

	}

}
