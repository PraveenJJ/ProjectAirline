package com.training.airline.utility;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.training.airline.dto.BookingDto;
import com.training.airline.dto.CityDto;
import com.training.airline.dto.FlightAvailDto;
import com.training.airline.dto.FlightDto;
import com.training.airline.dto.PassengerDto;
import com.training.airline.dto.UserDto;
import com.training.airline.model.Booking;
import com.training.airline.model.City;
import com.training.airline.model.Flight;
import com.training.airline.model.FlightAvail;
import com.training.airline.model.Passenger;
import com.training.airline.model.User;

/**
 * This is a utility class for the service layer. This class provides all the
 * methods for the conversion of model objects into dto object.
 * 
 * @author 251656
 */
public class DtoConverter {

	/**
	 * This method is used to convert the city object into city dto object.
	 * 
	 * @param city
	 * @return city dto object
	 */
	public static CityDto cityToCityDto(City city) {

		// instantiating the city dto object using all the details from the city model
		// object.
		CityDto cityDto = new CityDto(city.getCityId(), city.getCityName(), city.getCountry(), city.getAirport());

		// returning the city dto object
		return cityDto;

	}

	/**
	 * This method is used to convert the flight object into flight dto object.
	 * 
	 * @param flight
	 * @return flight dto object
	 */
	public static FlightDto flightToFlightDto(Flight flight) {

		// instantiating the flight dto object using all the details from the flight
		// model object.
		FlightDto flightDto = new FlightDto(flight.getFlightId(), flight.getDepartureTime(), flight.getArrivalTime());

		// getting the origin city object from the flight object
		City originCity = flight.getOriginCity();

		// converting the origin city object into origin city dto object
		CityDto originCityDto = DtoConverter.cityToCityDto(originCity);

		// setting the origin city dto object to the flight dto object
		flightDto.setOriginCity(originCityDto);

		// getting the destination city object from the flight object
		City destinationCity = flight.getDestinationCity();

		// converting the destination city object into destination city dto object
		CityDto destinationCityDto = DtoConverter.cityToCityDto(destinationCity);

		// setting the destination city dto object to the flight dto object
		flightDto.setDestinationCity(destinationCityDto);

		// returning the flight dto object
		return flightDto;

	}

	/**
	 * This method is used to convert the flightAvail object into flightAvail dto
	 * object.
	 * 
	 * @param flightAvail
	 * @return flight avail dto object
	 */
	public static FlightAvailDto flightAvailToFlightAvailDto(FlightAvail flightAvail) {

		// getting the flight object from the flight avail object
		Flight flight = flightAvail.getFlight();

		// converting the flight object into flight dto object
		FlightDto flightDto = DtoConverter.flightToFlightDto(flight);

		// instantiating the flight avail dto object using the details from the flight
		// avail object.
		FlightAvailDto flightAvailDto = new FlightAvailDto(flightAvail.getFlightDate(), flightAvail.getSeats(),
				flightAvail.getCost());

		// setting the flight dto object to the flight avail dto object
		flightAvailDto.setFlight(flightDto);

		// returning the flight avail dto object
		return flightAvailDto;

	}

	/**
	 * This method is used to convert the user object into user dto object.
	 * 
	 * @param user
	 * @return user dto object
	 */
	public static UserDto userToUserDto(User user) {

		// instantiating the user dto object using the details from the user object.
		UserDto userDto = new UserDto(user.getUserId(), user.getName(), user.getPassword(), user.getCreditCardNumber(),
				user.getCreditCardType(), user.getCreditCardMonth(), user.getCreditCardYear(), user.getAge());

		// returning the user dto object.
		return userDto;

	}

	/**
	 * This method is used to convert the booking object into booking dto object.
	 * 
	 * @param booking
	 * @return booking dto object
	 */
	public static BookingDto bookingToBookingDto(Booking booking) {

		// getting the user object from the booking object
		User user = booking.getUser();

		// converting the user object into user dto object
		UserDto userDto = DtoConverter.userToUserDto(user);

		// getting the flight avail object from the booking object
		FlightAvail flightAvail = booking.getFlightAvail();

		// converting the flight avail object into flight avail dto object
		FlightAvailDto flightAvailDto = DtoConverter.flightAvailToFlightAvailDto(flightAvail);

		// instantiating the booking dto object using the details from the booking
		// object
		BookingDto updatedBookingDto = new BookingDto(booking.getReferenceId(), userDto, flightAvailDto,
				booking.getStatus(), booking.getBookedSeats(), booking.getTotalCost());

		// getting the list of passengers from the booking object
		List<Passenger> passengers = booking.getPassengers();

		// new Array list for passengers dtos is created here
		List<PassengerDto> updatedPassengerDtoList = new ArrayList<>();

		// iterating over the passengers list
		for (Iterator<Passenger> iterator = passengers.iterator(); iterator.hasNext();) {

			// each passenger from the list
			Passenger passenger = iterator.next();

			// converting passenger into passenger dto object.
			PassengerDto passengerDto = DtoConverter.passengerToPassengerDto(passenger);

			// adding the passenger dto object to the list of passenger dtos
			updatedPassengerDtoList.add(passengerDto);

		}

		// setting the list of passenger dtos to the booking dto object
		updatedBookingDto.setPassengers(updatedPassengerDtoList);

		// returning the booking dto object.
		return updatedBookingDto;

	}

	/**
	 * This method is used to convert the passenger object into passenger dto
	 * object.
	 * 
	 * @param passenger
	 * @return passenger dto
	 */
	public static PassengerDto passengerToPassengerDto(Passenger passenger) {

		// instantiating the passenger dto object using the details from the passenger
		// object
		PassengerDto passengerDto = new PassengerDto(passenger.getTicketNumber(), passenger.getPassengerName(),
				passenger.getAge(), passenger.getStatus());

		// set the reference id for the passenger dto
		passengerDto.setReferenceId(passenger.getBooking().getReferenceId());

		// returning the passenger dto
		return passengerDto;

	}

	/**
	 * This method is used to convert the list of flightAvail objects into list
	 * flightAvail dtos objects.
	 * 
	 * @param flightAvailList
	 * @return list of flight avail dtos
	 */
	public static List<FlightAvailDto> flightAvailListToFlightAvailListDto(List<FlightAvail> flightAvailList) {

		// new Array list for flight avail dtos is created here
		List<FlightAvailDto> flightAvailDtoList = new ArrayList<>();

		// iterating over the list of flight avail
		for (Iterator<FlightAvail> iterator = flightAvailList.iterator(); iterator.hasNext();) {

			// each flight avail object from the list
			FlightAvail flightAvail = iterator.next();

			// flight avail object is converted into flight avail dto object
			FlightAvailDto flightAvailDto = DtoConverter.flightAvailToFlightAvailDto(flightAvail);

			// flight avail dto object is added to the list of flight avail dtos
			flightAvailDtoList.add(flightAvailDto);

		}

		// returning the list of flight avail dtos
		return flightAvailDtoList;

	}
}
