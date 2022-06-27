package com.training.airline.dto;

import java.io.Serializable;
import java.util.List;

/**
 * This is a Dto class to hold city information. It has private fields to
 * represent the properties of each City entity. It has getters and setters to
 * access and modify the private fields.
 * 
 * @author Praveen J
 */
public class CityDto implements Serializable {

	/**
	 * default serialVersionUID is used here.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This field cityId of type Integer represents the city id allocated to each
	 * city.	
	 */
	private Integer cityId;

	/**
	 * This field cityName of type String represents the name given for a city.
	 */
	private String cityName;

	/**
	 * This field country of type String represents the country to which the city
	 * belongs to.
	 */
	private String country;

	/**
	 * This field airport of type String represents the country to which the city
	 * belongs to.
	 */
	private String airport;

	/**
	 * This field represents the list of flights dto that has origin city as this City.
	 */
	private List<FlightDto> originCityFlights;

	/**
	 * This field represents the list of flights dto that has destination city as this City.
	 */
	private List<FlightDto> destinationCityFlights;

	/**
	 * This is a zero argument constructor. Used to instantiate city dto object.
	 */
	public CityDto() {

	}

	/**
	 * This is a parameterized constructor. Takes in the following parameters to
	 * instantiate the city Dto object and then initialize the values with the arguments
	 * passed.
	 * 
	 * @param cityId   --> city id
	 * @param cityName --> name of the city
	 * @param country  --> name of the country
	 * @param airport  --> name of the airport
	 */
	public CityDto(Integer cityId, String cityName, String country, String airport) {
		this.cityId = cityId;
		this.cityName = cityName;
		this.country = country;
		this.airport = airport;
	}

	/**
	 * This is a method used to get the city id from a city dto object
	 * 
	 * @return the city id given for each city dto.
	 */
	public Integer getCityId() {
		return cityId;
	}

	/**
	 * This is a method used to set the city id for a city dto object
	 * 
	 * @param cityId The id of the city is passed here.
	 */
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	/**
	 * This is a method used to get the city name from a city dto object
	 * 
	 * @return the city name given for each city
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * This is a method used to set the city name for a city dto object
	 * 
	 * @param cityName The name of the city is passed here.
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * This is a method used to get the country name from a city dto object
	 * 
	 * @return the country name to which the city belongs to.
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * This is a method used to set the country name for a city dto object
	 * 
	 * @param country The name of the country is passed here.
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * This is a method used to get the airport name from a city dto object
	 * 
	 * @return the airport name present in a city.
	 */
	public String getAirport() {
		return airport;
	}

	/**
	 * This is a method used to set the airport name for a city dto object
	 * 
	 * @param airport The name of the airport is passed here.
	 */
	public void setAirport(String airport) {
		this.airport = airport;
	}

	/**
	 * This is a method used to get the list of flights dto having origin city as this
	 * city.
	 * 
	 * @return the list of flights dto where the origin city is this city.
	 */
	public List<FlightDto> getOriginCityFlights() {
		return originCityFlights;
	}

	/**
	 * This is a method used to set the list of flights dto having origin city as this
	 * city.
	 * 
	 * @param originCityFlights List of flights dto having origin city as this city is
	 *                          passed here.
	 */
	public void setOriginCityFlights(List<FlightDto> originCityFlights) {
		this.originCityFlights = originCityFlights;
	}

	/**
	 * This is a method used to get the list of flights dto having destination city as
	 * this city.
	 * 
	 * @return the list of flights dto where the destination city is this city.
	 */
	public List<FlightDto> getDestinationCityFlights() {
		return destinationCityFlights;
	}

	/**
	 * This is a method used to set the list of flights dto having destination city as
	 * this city.
	 * 
	 * @param destinationCityFlights List of flights dto having destination city as this
	 *                               city is passed here.
	 */
	public void setDestinationCityFlights(List<FlightDto> destinationCityFlights) {
		this.destinationCityFlights = destinationCityFlights;
	}

}
