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
import com.training.airline.dto.PassengerDto;
import com.training.airline.dto.ValidateUser;
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
 * This is a service class for Booking. It has all the service methods related
 * to Booking Entity. All the services that are related to bookings are handled
 * by this service class.
 * 
 * @author 251656
 */
@Service
@Transactional
public class BookingService {

	/**
	 * This field bookingRepository of type BookingRepository is being autowired
	 * here in order to invoke methods from the BookingRepository interface.
	 */
	@Autowired
	private BookingRepository bookingRepository;

	/**
	 * This field passengerRepository of type PassengerRepository is being autowired
	 * here in order to invoke methods from the PassengerRepository interface.
	 */
	@Autowired
	private PassengerRepository passengerRepository;

	/**
	 * This field flightAvailRepository of type FlightAvailRepository is being
	 * autowired here in order to invoke methods from the FlightAvailRepository
	 * interface.
	 */
	@Autowired
	private FlightAvailRepository flightAvailRepository;

	/**
	 * Logger is instantiated with respect to BookingService to log errors occurring
	 * in this class.
	 */
	Logger logger = LoggerFactory.getLogger(BookingService.class);

	/**
	 * This method is used to get all the bookings made by the given user id, and
	 * return the list of bookings back to the controller.
	 * 
	 * @param validateUser, it holds the user id
	 * @return list of booking dtos
	 * @throws AirlineServiceException
	 */
	public List<BookingDto> getBookingForUserService(ValidateUser validateUser) throws AirlineServiceException {

		// new ArrayList for list of booking dtos is instantiated here
		List<BookingDto> bookingDtoList = new ArrayList<>();

		try {

			// getting the user id
			Integer userId = validateUser.getUserId();

			// if the validation of user id fails, if block gets executed
			if (!(Validation.integerValidation(userId))) {

				// Any exception that would occur is logged here.
				logger.error("User id is missing...");

				// Manually throwing new AirlineServiceException since user id is missing
				throw new AirlineServiceException("User id is missing...");

			}

			// getting the list of bookings for the given user id
			List<Booking> bookingList = bookingRepository.getBookingForUserRepository(userId);

			// if the list has no bookings, then if block gets executed
			if (bookingList.isEmpty()) {

				// Any exception that would occur is logged here.
				logger.error("There are no bookings for the given user...");

				// Manually throwing new AirlineServiceException since bookings are not
				// available
				throw new AirlineServiceException("There are no bookings for the given user...");

			}

			// iterating over the booking list
			for (Iterator<Booking> iterator = bookingList.iterator(); iterator.hasNext();) {

				// each booking from the list
				Booking booking = iterator.next();

				// booking is converted into booking dto
				BookingDto bookingDto = DtoConverter.bookingToBookingDto(booking);

				// booking dto is added to the list of booking dtos
				bookingDtoList.add(bookingDto);

			}

		} catch (SQLException e) {

			// Any exception that would occur is logged here.
			logger.error("An exception has occurred while getting bookings for the user... " + e.getMessage());

			// After catching SQLException, an AirlineServiceException is
			// thrown here manually.
			throw new AirlineServiceException(
					"An exception has occurred while getting bookings for the user... " + e.getMessage());

		} catch (Exception e) {

			// Any exception that would occur is logged here.
			logger.error("An exception has occurred while getting bookings for the user... " + e.getMessage());

			// After catching Exception, an AirlineServiceException is
			// thrown here manually.
			throw new AirlineServiceException(
					"An exception has occurred while getting bookings for the user... " + e.getMessage());

		}

		// returning the list of booking dtos to the controller
		return bookingDtoList;

	}

	/**
	 * This method is used to save the booking made by the user, and return the
	 * updated booking with reference id back to the controller.
	 * 
	 * @param bookingDto
	 * @return updated booking dto
	 * @throws AirlineServiceException
	 */
	public BookingDto saveBooking(BookingDto bookingDto) throws AirlineServiceException {

		// reference for updated booking dto is created here
		BookingDto bookingDtoUpdated;

//		if (bookingDto.getReferenceId() == -1) {

		// calling create booking method to create the booking for the passed booking
		// details
		bookingDtoUpdated = createBooking(bookingDto);

//		} 
//		else {
//			bookingDtoUpdated = updateBooking(bookingDto);
//		}

		// returning the updated booking dto to the controller
		return bookingDtoUpdated;
	}

	/**
	 * This method is used to create the booking made by the user, and return the
	 * updated booking with reference id back to save booking method.
	 * 
	 * @param bookingDto
	 * @return updated booking dto
	 * @throws AirlineServiceException
	 */
	private BookingDto createBooking(final BookingDto bookingDto) throws AirlineServiceException {

		// reference for updated booking dto is created here
		BookingDto updatedBookingDto = null;

		try {

			// validating the booking details
			Validation.bookingValidation(bookingDto);

			// getting the flight available for the given flight id on a given date
			FlightAvail flightAvail = flightAvailRepository.getFlightsAvailableRepository(
					bookingDto.getFlightAvail().getFlight().getFlightId(), bookingDto.getFlightAvail().getFlightDate());

			// if the number of seats for booking is more than the seats available, then if
			// block gets executed
			if (flightAvail.getSeats() < bookingDto.getBookedSeats()) {

				// Any exception that would occur is logged here.
				logger.error("The number of seats booked exceeds the number of seats that are available...");

				// Manually throwing new AirlineServiceException since number of seats booked
				// exceeds the limit
				throw new AirlineServiceException(
						"The number of seats booked exceeds the number of seats that are available...");

			}

			// calculating the total cost for the number of seats booked
			Float totalCostBooking = flightAvail.getCost() * bookingDto.getBookedSeats();

			// saving the booking to the database and getting the reference id of the saved
			// booking
			String referenceId = bookingRepository.callBookingSave(bookingDto.getUser().getUserId(),
					bookingDto.getFlightAvail().getFlight().getFlightId(), bookingDto.getFlightAvail().getFlightDate(),
					bookingDto.getBookedSeats(), totalCostBooking);

			// if the creation of booking fails, then if block gets executed
			if (referenceId.equalsIgnoreCase("Insertion failed")) {

				// Any exception that would occur is logged here.
				logger.error("Booking creation failed.");

				// Manually throwing new AirlineServiceException since booking creation failed
				throw new AirlineServiceException("Booking creation failed.");

			} else {

				// getting the list of passengers to be booked
				List<PassengerDto> passengers = bookingDto.getPassengers();

				// iterating over the list of passenger dtos
				for (Iterator<PassengerDto> iterator = passengers.iterator(); iterator.hasNext();) {

					// each passenger dto from the list
					PassengerDto passengerDto = iterator.next();

					// saving each passenger to the database
					String passengerSave = passengerRepository.callPassengerSave(Integer.parseInt(referenceId),
							passengerDto.getPassengerName(), passengerDto.getAge());

					// if saving the passenger fails, then if block gets executed
					if (passengerSave.equalsIgnoreCase("Insertion failed")) {

						// Any exception that would occur is logged here.
						logger.error("Passenger Insertion Failed...");

						// Manually throwing new AirlineServiceException since saving the passenger
						// failed
						throw new AirlineServiceException("Passenger Insertion Failed...");

					}

				}

				// getting the remaining number of available seats for the given flight for the
				// given date after booking is successful.
				Integer remainingSeats = flightAvail.getSeats() - bookingDto.getBookedSeats();

				// updating the remaining seats available for the given flight for the given
				// date
				int rowsUpdated = flightAvailRepository.updateFlightAvailSeatsRepository(remainingSeats,
						bookingDto.getFlightAvail().getFlight().getFlightId(),
						bookingDto.getFlightAvail().getFlightDate());

				// if updation of available seats fails, if block gets executed
				if (rowsUpdated == 0) {

					// Any exception that would occur is logged here.
					logger.error("Updating the seats in available seats failed.");

					// Manually throwing new AirlineServiceException since the updation of remaining
					// seats failed
					throw new AirlineServiceException("Updating the seats in available seats failed.");

				}

			}

			// getting the updated booking object from the database using the obtained
			// reference id
			Optional<Booking> optionalBooking = bookingRepository.findById(Integer.parseInt(referenceId));

			// if the booking is available, if block gets executed
			if (optionalBooking.isPresent()) {

				// getting the booking object
				Booking booking = optionalBooking.get();

				// converting the booking object into updated booking dto object
				updatedBookingDto = DtoConverter.bookingToBookingDto(booking);

			}

		} catch (SQLException e) {

			// Any exception that would occur is logged here.
			logger.error("An exception has occurred while creating the booking... " + e.getMessage());

			// After catching SQLException, an AirlineServiceException is
			// thrown here manually.
			throw new AirlineServiceException(
					"An exception has occurred while creating the booking... " + e.getMessage());

		} catch (ValidationFailureException e) {

			// Any exception that would occur is logged here.
			logger.error("An exception has occurred while creating the booking... " + e.getMessage());

			// After catching ValidationFailureException, an AirlineServiceException is
			// thrown here manually.
			throw new AirlineServiceException(
					"An exception has occurred while creating the booking... " + e.getMessage());

		} catch (Exception e) {

			// Any exception that would occur is logged here.
			logger.error("An exception has occurred while creating the booking... " + e.getMessage());

			// After catching Exception, an AirlineServiceException is
			// thrown here manually.
			throw new AirlineServiceException(
					"An exception has occurred while creating the booking... " + e.getMessage());

		}

		// returning the updated booking dto object back to save booking method
		return updatedBookingDto;

	}

//	private BookingDto updateBooking(final BookingDto bookingDto) {
//
//	}

	/**
	 * This method is used to cancel the booking made by the user, and return the
	 * updated booking with cancel status back to controller.
	 * 
	 * @param bookingDto
	 * @return updated booking dto object
	 * @throws AirlineServiceException
	 */
	public BookingDto cancelBookingService(BookingDto bookingDto) throws AirlineServiceException {

		// reference for updated booking dto is created here
		BookingDto updatedBookingDto = null;

		try {

			// getting the booking reference id
			Integer bookingReferenceId = bookingDto.getReferenceId();

			// if the validation of booking reference id fails, if block gets executed
			if (!(Validation.integerValidation(bookingReferenceId))) {

				// Any exception that would occur is logged here.
				logger.error("Booking reference id is missing...");

				// Manually throwing new AirlineServiceException since booking reference id is
				// missing
				throw new AirlineServiceException("Booking reference id is missing...");

			}

			// getting the booked seats
			Integer bookedSeats = bookingDto.getBookedSeats();

			// if the validation of booked seats fails, if block gets executed
			if (!(Validation.integerValidation(bookedSeats))) {

				// Any exception that would occur is logged here.
				logger.error("Booked seats is missing...");

				// Manually throwing new AirlineServiceException since booked seats is missing
				throw new AirlineServiceException("Booked seats is missing...");

			}

			// getting the booking object for the given reference id
			Optional<Booking> optionalBookingToCancel = bookingRepository.findById(bookingReferenceId);

			// if the booking object is not found, if block gets executed
			if (optionalBookingToCancel.isEmpty()) {

				// Any exception that would occur is logged here.
				logger.error("Booking not found for given reference id");

				// Manually throwing new AirlineServiceException since booking was not found
				throw new AirlineServiceException("Booking not found for given reference id");

			}

			// getting the booking object
			Booking bookingToCancel = optionalBookingToCancel.get();

			// getting the status of the booking object
			String bookingStatus = bookingToCancel.getStatus();

			// if the status of the booking is already cancelled, then if block gets
			// executed
			if (bookingStatus.equalsIgnoreCase("C")) {

				// Any exception that would occur is logged here.
				logger.error("Booking is already cancelled. You cannot cancel it again");

				// Manually throwing new AirlineServiceException since booking was already
				// cancelled
				throw new AirlineServiceException("Booking is already cancelled. You cannot cancel it again");

			}

			// calling cancel booking operation
			Integer cancelledBookings = bookingRepository.cancelBookingRepository(bookingDto.getReferenceId());

			// if the cancellation of the booking fails, if block gets executed
			if (cancelledBookings == 0) {

				// Any exception that would occur is logged here.
				logger.error("Booking cancel failed");

				// Manually throwing new AirlineServiceException since cancellation of the
				// booking failed
				throw new AirlineServiceException("Booking cancel failed");

			}

			// getting all the passengers having the given booking reference id
			List<Passenger> passengersToCancel = passengerRepository
					.getBookingPassengersRepository(bookingDto.getReferenceId());

			// initializing the variable
			Integer cancelledPassengers = 0;

			// iterating over the list of passengers
			for (Iterator<Passenger> iterator = passengersToCancel.iterator(); iterator.hasNext();) {

				// each passenger from the list
				Passenger passenger = iterator.next();

				// getting the status of each passenger
				String passengerStatus = passenger.getStatus();

				// if the status of the passenger is booked, if block gets executed
				if (passengerStatus.equalsIgnoreCase("B")) {

					// cancelling the passenger's ticket
					Integer rowsUpdated = passengerRepository.cancelPassengerByTicketRepository(
							passenger.getTicketNumber(), bookingDto.getReferenceId());

					// if the cancellation of passenger's ticket fails, if block gets executed
					if (rowsUpdated == 0) {

						// Any exception that would occur is logged here.
						logger.error("Passenger cancel failed");

						// Manually throwing new AirlineServiceException since cancellation of the
						// passenger failed
						throw new AirlineServiceException("Passenger cancel failed");

					}

					// incrementing the count of number of passengers' ticket cancelled
					cancelledPassengers++;

				}

			}

			// getting the updated booking object using the reference id
			Optional<Booking> optional = bookingRepository.findById(bookingDto.getReferenceId());

			// if the booking object is not available, if block gets executed
			if (optional.isEmpty()) {

				// Any exception that would occur is logged here.
				logger.error("Booking not found");

				// Manually throwing new AirlineServiceException since booking was not found
				throw new AirlineServiceException("Booking not found");

			}

			// getting the updated booking object
			Booking updatedBooking = optional.get();

			// get the flight available object
			FlightAvail flightsAvailable = flightAvailRepository.getFlightsAvailableRepository(
					updatedBooking.getFlightAvail().getFlight().getFlightId(),
					updatedBooking.getFlightAvail().getFlightDate());

			// if the number of passengers cancelled is greater than zero, if block gets
			// executed
			if (cancelledPassengers > 0) {

				// finding the remaining seats after the cancellation of the passengers
				Integer remainingSeats = flightsAvailable.getSeats() + cancelledPassengers;

				// updating the available seats
				int updatedRowsForFlightAvail = flightAvailRepository.updateFlightAvailSeatsRepository(remainingSeats,
						updatedBooking.getFlightAvail().getFlight().getFlightId(),
						updatedBooking.getFlightAvail().getFlightDate());

				// if the updation of the available seats fails, if block gets executed
				if (updatedRowsForFlightAvail == 0) {

					// Any exception that would occur is logged here.
					logger.error("Updating the seats in available seats failed.");

					// Manually throwing new AirlineServiceException since updation of seats
					// available failed.
					throw new AirlineServiceException("Updating the seats in available seats failed.");

				}

			}

			// converting the updated booking object into updated booking dto object.
			updatedBookingDto = DtoConverter.bookingToBookingDto(updatedBooking);

		} catch (SQLException e) {

			// Any exception that would occur is logged here.
			logger.error("An exception occurred:: " + e.getMessage());

			// After catching SQLException, an AirlineServiceException is
			// thrown here manually.
			throw new AirlineServiceException("An exception occurred:: " + e.getMessage());

		} catch (Exception e) {

			// Any exception that would occur is logged here.
			logger.error("An exception occurred:: " + e.getMessage());

			// After catching Exception, an AirlineServiceException is
			// thrown here manually.
			throw new AirlineServiceException("An exception occurred:: " + e.getMessage());

		}

		// returning the updated booking dto object to the controller
		return updatedBookingDto;

	}

	/**
	 * This method is used to get the booking made by the user using the reference
	 * id, and return the booking details back to the controller
	 * 
	 * @param bookingDto, holds the reference id
	 * @return booking dto object
	 * @throws AirlineServiceException
	 */
	public BookingDto getBookingByReferenceIdService(BookingDto bookingDto) throws AirlineServiceException {

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

		// getting the booking using reference id
		Optional<Booking> optionalBooking = bookingRepository.findById(referenceId);

		// if the booking is not found, if block gets executed
		if (optionalBooking.isEmpty()) {

			// Any exception that would occur is logged here.
			logger.error("Booking not found");

			// Manually throwing new AirlineServiceException since booking was not found
			throw new AirlineServiceException("Booking not found");

		}

		// getting the booking object
		Booking booking = optionalBooking.get();

		// converting the booking object into booking dto object
		BookingDto updatedBookingDto = DtoConverter.bookingToBookingDto(booking);

		// returning the booking dto object back to the controller
		return updatedBookingDto;

	}

}
