package com.training.airline.dto;

import java.io.Serializable;
import java.sql.Date;

/**
 * This is a Dto class to hold flight information which are available on a date
 * range. It has private fields to represent the properties of each flight. It
 * has getters and setters to access and modify the private fields.
 * 
 * @author 251656
 */
public class FlightsAvailableDateRangeDto implements Serializable {

	/**
	 * default serialVersionUID is used here.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This field flightId of type String represents the flight id.
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
	 * This field startDate of type Date represents the start date for the range of
	 * search.
	 */
	private Date startDate;

	/**
	 * This field endDate of type Date represents the end date for the range of
	 * search.
	 */
	private Date endDate;

	/**
	 * This is a zero argument constructor. Used to instantiate flight available dto
	 * on a given date range object.
	 */
	public FlightsAvailableDateRangeDto() {

	}

	/**
	 * This is a parameterized constructor. Takes in the following parameters to
	 * instantiate the flight available dto on a given date range object and then
	 * initialize the values with the arguments passed.
	 * 
	 * @param flightId        ---> the flight id
	 * @param originCity      ---> origin city name
	 * @param destinationCity ---> destination city name
	 * @param startDate       ---> start date for search range
	 * @param endDate         ---> end date for search range
	 */
	public FlightsAvailableDateRangeDto(String flightId, String originCity, String destinationCity, Date startDate,
			Date endDate) {
		this.flightId = flightId;
		this.originCity = originCity;
		this.destinationCity = destinationCity;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	/**
	 * This is a method used to get the flight id from a flight available dto on a
	 * given date range object
	 * 
	 * @return the flight id given for each flight available dto on a given date
	 *         range object.
	 */
	public String getFlightId() {
		return flightId;
	}

	/**
	 * This is a method used to set the flight id for a flight available dto on a
	 * given date range object
	 * 
	 * @param flightId The flight id is passed here.
	 */
	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	/**
	 * This is a method used to get the origin city name from a flight available dto
	 * on a given date range object
	 * 
	 * @return the origin city name given for each flight available dto object.
	 */
	public String getOriginCity() {
		return originCity;
	}

	/**
	 * This is a method used to set the origin city name for a flight available dto
	 * on a given date range object
	 * 
	 * @param originCity The origin city name is passed here.
	 */
	public void setOriginCity(String originCity) {
		this.originCity = originCity;
	}

	/**
	 * This is a method used to get the destination city name from a flight
	 * available dto on a given date range object
	 * 
	 * @return the destination city name given for each flight available dto on a
	 *         given date range object.
	 */
	public String getDestinationCity() {
		return destinationCity;
	}

	/**
	 * This is a method used to set the destination city name for a flight available
	 * dto on a given date range object
	 * 
	 * @param destinationCity The destination city name is passed here.
	 */
	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}

	/**
	 * This is a method used to get the start date for the flight search
	 * 
	 * @return the start date for the flight search
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * This is method used to set the start date for the flight search
	 * 
	 * @param startDate The start date is passed here.
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * This is a method used to get the end date for the flight search
	 * 
	 * @return the end date for the flight search
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * This is method used to set the end date for the flight search
	 * 
	 * @param endDate The end date is passed here.
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
