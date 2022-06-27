package com.training.airline.dto;

import java.io.Serializable;
import java.util.List;

/**
 * This is a Dto class to hold booking information. It has private fields to
 * represent the properties of each Booking entity. It has getters and setters
 * to access and modify the private fields.
 * 
 * @author Praveen J
 */
public class BookingDto implements Serializable {

	/**
	 * default serialVersionUID is used here.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This field referenceId of type Integer represents the booking reference id
	 * allocated to each booking.
	 */
	private Integer referenceId;

	/**
	 * This field user of type User dto represents the user dto object.
	 */
	private UserDto user;

	/**
	 * This field flight of type FlightAvailDto represents the flight available dto
	 * object.
	 */
	private FlightAvailDto flightAvail; 

	/**
	 * This field status of type String represents the status for a booking.
	 */
	private String status;

	/**
	 * This field bookedSeats of type Integer represents the number of seats booked
	 * for a booking.
	 */
	private Integer bookedSeats;

	/**
	 * This field represents the total cost for all the seats that is booked in the
	 * booking.
	 */
	private Float totalCost;

	/**
	 * This field represents the list of passengers dto having the reference id as
	 * this booking.
	 */
	private List<PassengerDto> passengers;

	/**
	 * This is a zero argument constructor. Used to instantiate booking dto object.
	 */
	public BookingDto() {

	}

	/**
	 * This is a parameterized constructor. Takes in the following parameters to
	 * instantiate the booking dto object and then initialize the values with the
	 * arguments passed.
	 * 
	 * @param referenceId ---> reference id of the booking
	 * @param status      ---> status of the booking
	 * @param bookedSeats ---> number of booked seats
	 * @param totalCost   ---> total cost for all the seats booked
	 */
	public BookingDto(Integer referenceId, String status, Integer bookedSeats, Float totalCost) {
		this.referenceId = referenceId;
		this.status = status;
		this.bookedSeats = bookedSeats;
		this.totalCost = totalCost;
	}

	/**
	 * This is a parameterized constructor. Takes in the following parameters to
	 * instantiate the booking dto object and then initialize the values with the
	 * arguments passed.
	 * 
	 * @param referenceId ---> reference id of the booking
	 * @param user		  ---> user dto of the booking
	 * @param flightAvail ---> flight available dto of the booking
	 * @param status      ---> status of the booking
	 * @param bookedSeats ---> number of booked seats
	 * @param totalCost   ---> total cost for all the seats booked
	 */
	public BookingDto(Integer referenceId, UserDto user, FlightAvailDto flightAvail, String status, Integer bookedSeats,
			Float totalCost) {
		this.referenceId = referenceId;
		this.user = user;
		this.flightAvail = flightAvail; 
		this.status = status;
		this.bookedSeats = bookedSeats;
		this.totalCost = totalCost;
	}

	/**
	 * This is a method used to get the reference id from a booking dto object
	 * 
	 * @return the reference id given for each booking.
	 */
	public Integer getReferenceId() {
		return referenceId;
	}

	/**
	 * This is a method used to set the reference id for a booking dto object
	 * 
	 * @param referenceId The reference id of the booking is passed here.
	 */
	public void setReferenceId(Integer referenceId) {
		this.referenceId = referenceId;
	}

	/**
	 * This is a method used to get the user dto from a booking dto object
	 * 
	 * @return the user dto given for each booking.
	 */
	public UserDto getUser() {
		return user;
	}

	/**
	 * This is a method used to set the user dto for a booking dto object
	 * 
	 * @param user The user dto of the booking is passed here.
	 */
	public void setUser(UserDto user) {
		this.user = user;
	}

	/**
	 * This is a method used to get the flight available dto object from a booking
	 * dto object
	 * 
	 * @return the flight available dto object given for each booking.
	 */
	public FlightAvailDto getFlightAvail() {
		return flightAvail; 
	}

	/**
	 * This is a method used to set the flight available dto object for a booking dto object
	 * 
	 * @param flightAvail The flight available dto object of the booking is passed here.
	 */
	public void setFlightAvail(FlightAvailDto flightAvail) {
		this.flightAvail = flightAvail; 
	}

	/**
	 * This is a method used to get the status from a booking dto object
	 * 
	 * @return the status given for each booking.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * This is a method used to set the status for a booking dto object
	 * 
	 * @param status The status of the booking is passed here.
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * This is a method used to get the number of booked seats from a booking dto object
	 * 
	 * @return the number of booked seats given for each booking.
	 */
	public Integer getBookedSeats() {
		return bookedSeats;
	}

	/**
	 * This is a method used to set the number of booking seats for a booking dto object
	 * 
	 * @param bookedSeats The number of booking seats is passed here.
	 */
	public void setBookedSeats(Integer bookedSeats) {
		this.bookedSeats = bookedSeats;
	}

	/**
	 * This is a method used to get the total cost for all seats from a booking
	 * dto object
	 * 
	 * @return the total cost for all seats given for each booking.
	 */
	public Float getTotalCost() {
		return totalCost;
	}

	/**
	 * This is a method used to set the total cost for all seats for a booking
	 * dto object
	 * 
	 * @param totalCost The total cost for all seats is passed here.
	 */
	public void setTotalCost(Float totalCost) {
		this.totalCost = totalCost;
	}

	/**
	 * This is a method used to get the list of passengers dto from a booking dto object
	 * 
	 * @return the list of passengers dto given for each booking.
	 */
	public List<PassengerDto> getPassengers() {
		return passengers;
	}

	/**
	 * This is a method used to set the list of passengers dto for a booking dto object
	 * 
	 * @param passenger The list of passengers dto is passed here.
	 */
	public void setPassengers(List<PassengerDto> passengers) {
		this.passengers = passengers;
	}

}
