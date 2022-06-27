package com.training.airline.dto;

import java.io.Serializable;
import java.sql.Date;

/**
 * This is a Dto class to hold flight available on date information. It has
 * private fields to represent the properties of each Flight information. It has
 * getters and setters to access and modify the private fields.
 * 
 * @author Praveen J
 */
public class FlightsAvailableOnDateDto implements Serializable {

	/**
	 * default serialVersionUID is used here.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This field flightId of type String represents the flight id allocated to each
	 * flight.
	 */
	private String flightId;

	/**
	 * This field originCity of type String represents the origin city name.
	 */
	private String originCity;

	/**
	 * This field destinationCity of type String represents the destination city
	 * name.
	 */
	private String destinationCity;

	/**
	 * This field travelOn of type Date represents the date for the flight
	 */
	private Date travelOn;

	/**
	 * This is a zero argument constructor. Used to instantiate flight available dto
	 * on a given date object.
	 */
	public FlightsAvailableOnDateDto() {

	}

	/**
	 * This is a parameterized constructor. Takes in the following parameters to
	 * instantiate the flight available dto on a given date object and then
	 * initialize the values with the arguments passed.
	 * 
	 * @param flightId ---> the flight id
	 * @param travelOn ---> date for the flight
	 */
	public FlightsAvailableOnDateDto(String flightId, Date travelOn) {
		this.flightId = flightId;
		this.travelOn = travelOn;
	}

	/**
	 * This is a parameterized constructor. Takes in the following parameters to
	 * instantiate the flight available dto on a given date object and then
	 * initialize the values with the arguments passed.
	 * 
	 * @param flightId        ---> the flight id
	 * @param originCity      ---> origin city name
	 * @param destinationCity ---> destination city name
	 * @param travelOn        ---> date for the flight
	 */
	public FlightsAvailableOnDateDto(String flightId, String originCity, String destinationCity, Date travelOn) {
		this.flightId = flightId;
		this.originCity = originCity;
		this.destinationCity = destinationCity;
		this.travelOn = travelOn;
	}

	/**
	 * This is a method used to get the flight id from a flight available dto on a
	 * given date object
	 * 
	 * @return the flight id given for each flight available dto on a given date
	 *         object.
	 */
	public String getFlightId() {
		return flightId;
	}

	/**
	 * This is a method used to set the flight id for a flight available dto on a
	 * given date object
	 * 
	 * @param flightId The flight id is passed here.
	 */
	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	/**
	 * This is a method used to get the travel date for the flight search
	 * 
	 * @return the travel date for the flight search
	 */
	public Date getTravelOn() {
		return travelOn;
	}

	/**
	 * This is method used to set the travel date for the flight search
	 * 
	 * @param startDate The travel date is passed here.
	 */
	public void setTravelOn(Date travelOn) {
		this.travelOn = travelOn;
	}

	/**
	 * This is a method used to get the origin city name from a flight available dto
	 * on a given date object
	 * 
	 * @return the origin city name given for each flight available dto on a given
	 *         date object.
	 */
	public String getOriginCity() {
		return originCity;
	}

	/**
	 * This is a method used to set the origin city name for a flight available dto
	 * on a given date object
	 * 
	 * @param originCity The origin city name is passed here.
	 */
	public void setOriginCity(String originCity) {
		this.originCity = originCity;
	}

	/**
	 * This is a method used to get the destination city name from a flight
	 * available dto on a given date object
	 * 
	 * @return the destination city name given for each flight available dto on a
	 *         given date object.
	 */
	public String getDestinationCity() {
		return destinationCity;
	}

	/**
	 * This is a method used to set the destination city name for a flight available
	 * dto on a given date object
	 * 
	 * @param destinationCity The destination city name is passed here.
	 */
	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}

}
