package com.training.airline.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.training.airline.dto.BookingDto;
import com.training.airline.dto.CancelPassengerDto;
import com.training.airline.dto.PassengerDto;
import com.training.airline.exception.AirlineServiceException;
import com.training.airline.exception.ValidationFailureException;
import com.training.airline.model.Booking;
import com.training.airline.model.FlightAvail;
import com.training.airline.model.Passenger;
import com.training.airline.repository.BookingRepository;
import com.training.airline.repository.FlightAvailRepository;
import com.training.airline.repository.PassengerRepository;
import com.training.airline.utility.DtoConverter;
import com.training.airline.utility.Validation;

/**
 * This is a service class for Passenger. It has all the service methods related
 * to Passenger Entity. All the services that are related to passengers are
 * handled by this service class.
 * 
 * @author Praveen J
 */
@Service
@Transactional
public class PassengerService {

	/**
	 * This field passengerRepository of type PassengerRepository is being autowired
	 * here in order to invoke methods from the PassengerRepository interface.
	 */
	@Autowired
	private PassengerRepository passengerRepository;

	/**
	 * This field bookingRepository of type BookingRepository is being autowired
	 * here in order to invoke methods from the BookingRepository interface.
	 */
	@Autowired
	private BookingRepository bookingRepository;

	/**
	 * This field flightAvailRepository of type FlightAvailRepository is being
	 * autowired here in order to invoke methods from the FlightAvailRepository
	 * interface.
	 */
	@Autowired
	private FlightAvailRepository flightAvailRepository;

	/**
	 * Logger is instantiated with respect to PassengerService to log errors
	 * occurring in this class.
	 */
	Logger logger = LoggerFactory.getLogger(PassengerService.class);

	/**
	 * This method is used to get all the passengers for the given booking reference
	 * id, and return the list of passengers back to the controller.
	 * 
	 * @param bookingDto
	 * @return list of passenger dtos
	 * @throws AirlineServiceException
	 */
	public List<PassengerDto> getBookingPassengersService(BookingDto bookingDto) throws AirlineServiceException {

		// new ArrayList for the list of passengers dtos is created here.
		List<PassengerDto> passengerDtoList = new ArrayList<>();

		try {

			// getting the booking reference id
			Integer referenceId = bookingDto.getReferenceId();

			// if the validation of booking reference id fails, if block gets executed
			if (!(Validation.integerValidation(referenceId))) {

				// Any exception that would occur is logged here.
				logger.error("Booking reference id is missing...");

				// Manually throwing new AirlineServiceException since booking reference id is
				// missing
				throw new AirlineServiceException("Booking reference id is missing...");

			}

			// getting the list of passengers for the given booking reference id
			List<Passenger> passengersList = passengerRepository
					.getBookingPassengersRepository(bookingDto.getReferenceId());

			// if the passengers are not found for the given booking reference id, if block
			// gets executed
			if (passengersList.isEmpty()) {

				// Any exception that would occur is logged here.
				logger.error("There are no passengers for the given booking id...");

				// Manually throwing new AirlineServiceException since passengers are not
				// found for the given booking reference id
				throw new AirlineServiceException("There are no passengers for the given booking id...");

			}

			// iterating over the list of passengers
			for (Iterator<Passenger> iterator = passengersList.iterator(); iterator.hasNext();) {

				// each passenger from the list
				Passenger passenger = iterator.next();

				// passenger object is converted into passenger dto object
				PassengerDto passengerDto = DtoConverter.passengerToPassengerDto(passenger);

				// passenger dto is added to the list of passenger dtos
				passengerDtoList.add(passengerDto);

			}

		} catch (SQLException e) {

			// Any exception that would occur is logged here.
			logger.error("An exception occurred while getting the passenger details... " + e.getMessage());

			// After catching SQLException, an AirlineServiceException is
			// thrown here manually.
			throw new AirlineServiceException(
					"An exception occurred while getting the passenger details... " + e.getMessage());

		} catch (Exception e) {

			// Any exception that would occur is logged here.
			logger.error("An exception occurred while getting the passenger details... " + e.getMessage());

			// After catching Exception, an AirlineServiceException is
			// thrown here manually.
			throw new AirlineServiceException(
					"An exception occurred while getting the passenger details... " + e.getMessage());

		}

		// returning the list of passenger dtos to the controller
		return passengerDtoList;

	}

	/**
	 * This method is used to cancel the list passengers for a booking reference id,
	 * and return the updated booking back to the controller.
	 * 
	 * @param cancelPassengerDto
	 * @return updated booking dto
	 * @throws AirlineServiceException
	 */
	public BookingDto cancelPassengerByTicketService(CancelPassengerDto cancelPassengerDto)
			throws AirlineServiceException {

		// reference for the updated booking dto is created here
		BookingDto updatedBookingDto = null;

		try {

			// validating the passenger details
			Validation.cancelPassengerValidation(cancelPassengerDto);

			// getting the list of passenger dtos
			List<PassengerDto> passengerDtos = cancelPassengerDto.getPassengerDtos();

			// initializing the variable
			Integer cancelledPassengers = 0;

			// iterating over the list of passenger dtos
			for (Iterator<PassengerDto> iterator = passengerDtos.iterator(); iterator.hasNext();) {

				// each passenger dto from the list
				PassengerDto passengerDto = iterator.next();

				// finding the passenger object using the ticket number
				Optional<Passenger> optionalPassenger = passengerRepository.findById(passengerDto.getTicketNumber());

				// if the passenger is not found, if block gets executed
				if (optionalPassenger.isEmpty()) {

					// Any exception that would occur is logged here.
					logger.error("Passenger is not available for the given ticket number");

					// Manually throwing new AirlineServiceException since passenger is not
					// available
					throw new AirlineServiceException("Passenger is not available for the given ticket number");

				}

				// getting the passenger object
				Passenger passengerToCancel = optionalPassenger.get();

				// getting the status of the passenger
				String passengerStatus = passengerToCancel.getStatus();

				// if the status of the passenger is booked, if block gets executed
				if (passengerStatus.equalsIgnoreCase("B")) {

					// cancelling the tickey for the passenger
					Integer rowsUpdated = passengerRepository.cancelPassengerByTicketRepository(
							passengerDto.getTicketNumber(), cancelPassengerDto.getReferenceId());

					// if the cancellation of the passenger fails, if block gets executed
					if (rowsUpdated == 0) {

						// Any exception that would occur is logged here.
						logger.error("Passenger ticket cancellation failed");

						// Manually throwing new AirlineServiceException since cancellation of passenger
						// ticket has failed
						throw new AirlineServiceException("Passenger ticket cancellation failed");

					}

					// incrementing the count of number of passenger tickets cancelled.
					cancelledPassengers++;

				}

			}

			// getting the booking object
			Optional<Booking> optionalBooking = bookingRepository.findById(cancelPassengerDto.getReferenceId());

			// if the booking object is not found, if block gets executed
			if (optionalBooking.isEmpty()) {

				// Any exception that would occur is logged here.
				logger.error("Booking was not found for the given reference id");

				// Manually throwing new AirlineServiceException since booking is not
				// available
				throw new AirlineServiceException("Booking was not found for the given reference id");

			}

			// getting the booking object
			Booking booking = optionalBooking.get();

			// getting the count of number of cancelled tickets for a given booking
			// reference id
			Integer cancelledTicketsCount = passengerRepository
					.getCancelledTicketsCount(cancelPassengerDto.getReferenceId());

			// if the booked seats from the booking is equal to the number of cancelled
			// tickets, if block gets executed
			if (booking.getBookedSeats() == cancelledTicketsCount) {

				// cancelling the booking for the given reference id
				Integer cancelBookingRepository = bookingRepository
						.cancelBookingRepository(cancelPassengerDto.getReferenceId());

				// if the cancellation of the booking fails, if block gets executed
				if (cancelBookingRepository == 0) {

					// Any exception that would occur is logged here.
					logger.error("Booking cancellation failed");

					// Manually throwing new AirlineServiceException since booking cancellation has
					// failed.
					throw new AirlineServiceException("Booking cancellation failed");

				}

				// setting the status of the booking as cancelled aftern the cancellation is
				// done on the database
				booking.setStatus("C");

			}

			// getting the flights available object for the given flight id for the given
			// date.
			FlightAvail flightsAvailable = flightAvailRepository.getFlightsAvailableRepository(
					booking.getFlightAvail().getFlight().getFlightId(), booking.getFlightAvail().getFlightDate());

			// if the number of cancelled passengers is greater than zero, if block gets
			// executed
			if (cancelledPassengers > 0) {

				// calculating the remaining seats after the cancellation of the passengers
				Integer remainingSeats = flightsAvailable.getSeats() + cancelledPassengers;

				// updating the available seats for the given flight id on the given date
				int updatedRowsForFlightAvail = flightAvailRepository.updateFlightAvailSeatsRepository(remainingSeats,
						booking.getFlightAvail().getFlight().getFlightId(), booking.getFlightAvail().getFlightDate());

				// if the updation of available seats fails, if block gets executed
				if (updatedRowsForFlightAvail == 0) {

					// Any exception that would occur is logged here.
					logger.error("Updating the seats in available seats failed.");

					// Manually throwing new AirlineServiceException since updation of the available
					// seats has failed.
					throw new AirlineServiceException("Updating the seats in available seats failed.");

				}

			}

			// getting the updated booking object
			Booking updatedBooking = bookingRepository
					.getBookingByReferenceIdRepository(cancelPassengerDto.getReferenceId());

			// getting the list of updated passengers
			List<Passenger> updatedPassengers = passengerRepository
					.getBookingPassengersRepository(cancelPassengerDto.getReferenceId());

			// setting the list of updated passengers to the updated booking object
			updatedBooking.setPassengers(updatedPassengers);

			// converting updated booking object into updated booking dto object
			updatedBookingDto = DtoConverter.bookingToBookingDto(updatedBooking);

		} catch (SQLException e) {

			// Any exception that would occur is logged here.
			logger.error("An exception occurred:: " + e.getMessage());

			// After catching SQLException, an AirlineServiceException is
			// thrown here manually.
			throw new AirlineServiceException("An exception occurred:: " + e.getMessage());

		} catch (ValidationFailureException e) {

			// Any exception that would occur is logged here.
			logger.error("An exception has occurred while cancelling the passengers... " + e.getMessage());

			// After catching ValidationFailureException, an AirlineServiceException is
			// thrown here manually.
			throw new AirlineServiceException(
					"An exception has occurred while cancelling the passengers... " + e.getMessage());

		} catch (Exception e) {

			// Any exception that would occur is logged here.
			logger.error("An exception occurred:: " + e.getMessage());

			// After catching Exception, an AirlineServiceException is
			// thrown here manually.
			throw new AirlineServiceException("An exception occurred:: " + e.getMessage());

		}

		// returning the updated booking dto object to the controller.
		return updatedBookingDto;

	}

}
