package com.training.airline.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * This is a Dto class to hold flight avail information. It has private fields
 * to represent the properties of each flight avail entity. It has getters and
 * setters to access and modify the private fields.
 * 
 * @author 251656
 */
public class FlightAvailDto implements Serializable {

	/**
	 * default serialVersionUID is used here.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This field flightId of type Flight dto represents the flight object. It is used
	 * to obtain a relation between Flight and FlightAvail using the field flight id
	 * as a join.
	 */
	private FlightDto flight; 

	/**
	 * This field represents the flight date.
	 * 
	 * @JsonFormat is used here to specify the pattern and time zone for date while
	 *             converting to JSON.
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
	private Date flightDate;

	/**
	 * This field represents the number of seats available to book.
	 */
	private Integer seats;

	/**
	 * This field represents the cost for each seat that is available to book.
	 */
	private Float cost;

	/**
	 * This field represents the list of bookings dto for this flight id on a given date.
	 */
	private List<BookingDto> flightBookings;

	/**
	 * This is a zero argument constructor. Used to instantiate flight available dto
	 * object.
	 */
	public FlightAvailDto() {

	}

	/**
	 * This is a parameterized constructor. Takes in the following parameters to
	 * instantiate the flight available dto object and then initialize the values
	 * with the arguments passed.
	 * 
	 * @param flightDate ---> The flight date
	 * @param seats      ---> The number of seats available to book
	 * @param cost       ---> The cost of each seat
	 */
	public FlightAvailDto(Date flightDate, Integer seats, Float cost) {
		this.flightDate = flightDate;
		this.seats = seats;
		this.cost = cost;
	}

	/**
	 * This is a method used to get the flight dto object from a flight available
	 * dto object
	 * 
	 * @return the flight dto object given for each flight available dto object.
	 */
	public FlightDto getFlight() { 
		return flight;
	}

	/**
	 * This is a method used to set the flight dto object for a flight available dto
	 * object
	 * 
	 * @param flight The flight dto object is passed here to set the flight id
	 *                 reference.
	 */
	public void setFlight(FlightDto flight) { 
		this.flight = flight;
	}

	/**
	 * This is a method used to get the flight date from a flight available dto
	 * object
	 * 
	 * @return the flight date given for each flight available dto object.
	 */
	public Date getFlightDate() {
		return flightDate;
	}

	/**
	 * This is a method used to set the flight date for a flight available dto
	 * object
	 * 
	 * @param flightDate The flight date is passed here.
	 */
	public void setFlightDate(Date flightDate) {
		this.flightDate = flightDate;
	}

	/**
	 * This is a method used to get the seats available from a flight available dto
	 * object
	 * 
	 * @return the seats available for each flight available dto object.
	 */
	public Integer getSeats() {
		return seats;
	}

	/**
	 * This is a method used to set the seats available for a flight available dto
	 * object
	 * 
	 * @param seats The seats available is passed here.
	 */
	public void setSeats(Integer seats) {
		this.seats = seats;
	}

	/**
	 * This is a method used to get the cost of each seat from a flight available
	 * dto object
	 * 
	 * @return the cost for each seat for a flight available dto object.
	 */
	public Float getCost() {
		return cost;
	}

	/**
	 * This is a method used to set the cost of each seat available for a flight dto
	 * available object
	 * 
	 * @param cost The cost of each seat available is passed here.
	 */
	public void setCost(Float cost) {
		this.cost = cost;
	}

	/**
	 * This is a method used to get the list of flight bookings dto from a flight
	 * dto available object
	 * 
	 * @return the list of flight bookings dto for this flight on a given date.
	 */
	public List<BookingDto> getFlightBookings() {
		return flightBookings;
	}

	/**
	 * This is a method used to set the list of flight bookings dto for a flight
	 * available dto object
	 * 
	 * @param flightBookings List of flight bookings dto is passed here.
	 */
	public void setFlightBookings(List<BookingDto> flightBookings) {
		this.flightBookings = flightBookings;
	}

}
