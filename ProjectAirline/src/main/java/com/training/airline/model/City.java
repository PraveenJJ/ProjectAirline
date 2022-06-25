package com.training.airline.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * This is a model class to hold city information. It resembles the table in the
 * database under the name "alr_cities". It has private fields to represent the
 * properties of each City entity. It has getters and setters to access and
 * modify the private fields.
 * 
 * @author 251656
 */
@Entity(name = "alr_cities")
public class City implements Serializable {

	/**
	 * default serialVersionUID is used here.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This field cityId of type Integer represents the city id allocated to each
	 * city. It is a primary key, and hence it is annotated with @Id. It is to be
	 * auto-incremented by the database, and so the GenerationType for Id is
	 * IDENTITY. It is mapped to a column named "city_id" in the database.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "city_id")
	private Integer cityId;

	/**
	 * This field cityName of type String represents the name given for a city. It
	 * is mapped to a column named "city_name" in the database.
	 */
	@Column(name = "city_name")
	private String cityName;

	/**
	 * This field country of type String represents the country to which the city
	 * belongs to. It is mapped to a column named "country" in the database.
	 */
	@Column(name = "country")
	private String country;

	/**
	 * This field airport of type String represents the country to which the city
	 * belongs to. It is mapped to a column named "country" in the database.
	 */
	@Column(name = "airport")
	private String airport;

	/**
	 * This is a bidirectional mapping, which represents the list of flights that
	 * has origin city as this City. It is mapped by a variable named "originCity"
	 * in Flight model class.
	 * 
	 * @JsonIgnore is used to avoid the circular dependency that would occur between
	 *             City and Flight.
	 */
	@OneToMany(mappedBy = "originCity")
	@JsonIgnore
	private List<Flight> originCityFlights;

	/**
	 * This is a bidirectional mapping, which represents the list of flights that
	 * has destination city as this City. It is mapped by a variable named
	 * "destinationCity" in Flight model class.
	 * 
	 * @JsonIgnore is used to avoid the circular dependency that would occur between
	 *             City and Flight.
	 */
	@OneToMany(mappedBy = "destinationCity")
	@JsonIgnore
	private List<Flight> destinationCityFlights;

	/**
	 * This is a zero argument constructor. Used to instantiate city object.
	 */
	public City() {

	}

	/**
	 * This is a parameterized constructor. Takes in the following parameters to
	 * instantiate the city object and then initialize the values with the arguments
	 * passed.
	 * 
	 * @param cityName --> name of the city
	 * @param country  --> name of the country
	 * @param airport  --> name of the airport
	 */
	public City(String cityName, String country, String airport) {
		this.cityName = cityName;
		this.country = country;
		this.airport = airport;
	}

	/**
	 * This is a method used to get the city id from a city object
	 * 
	 * @return the city id given for each city.
	 */
	public Integer getCityId() {
		return cityId;
	}

	/**
	 * This is a method used to set the city id for a city object
	 * 
	 * @param cityId The id of the city is passed here.
	 */
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	/**
	 * This is a method used to get the city name from a city object
	 * 
	 * @return the city name given for each city
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * This is a method used to set the city name for a city object
	 * 
	 * @param cityName The name of the city is passed here.
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * This is a method used to get the country name from a city object
	 * 
	 * @return the country name to which the city belongs to.
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * This is a method used to set the country name for a city object
	 * 
	 * @param country The name of the country is passed here.
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * This is a method used to get the airport name from a city object
	 * 
	 * @return the airport name present in a city.
	 */
	public String getAirport() {
		return airport;
	}

	/**
	 * This is a method used to set the airport name for a city object
	 * 
	 * @param airport The name of the airport is passed here.
	 */
	public void setAirport(String airport) {
		this.airport = airport;
	}

	/**
	 * This is a method used to get the list of flights having origin city as this
	 * city.
	 * 
	 * @return the list of flights where the origin city is this city.
	 */
	public List<Flight> getOriginCityFlights() {
		return originCityFlights;
	}

	/**
	 * This is a method used to set the list of flights having origin city as this
	 * city.
	 * 
	 * @param originCityFlights List of flights having origin city as this city is
	 *                          passed here.
	 */
	public void setOriginCityFlights(List<Flight> originCityFlights) {
		this.originCityFlights = originCityFlights;
	}

	/**
	 * This is a method used to get the list of flights having destination city as
	 * this city.
	 * 
	 * @return the list of flights where the destination city is this city.
	 */
	public List<Flight> getDestinationCityFlights() {
		return destinationCityFlights;
	}

	/**
	 * This is a method used to set the list of flights having destination city as
	 * this city.
	 * 
	 * @param destinationCityFlights List of flights having destination city as this
	 *                               city is passed here.
	 */
	public void setDestinationCityFlights(List<Flight> destinationCityFlights) {
		this.destinationCityFlights = destinationCityFlights;
	}

	/**
	 * hashCode() from the Object class is overridden with respect to the city
	 * class. Objects.hash(Object... values) is invoked with the parameters as shown
	 * below.
	 * 
	 * @return the integer obtained from the hash method.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(airport, cityId, cityName, country);
	}

	/**
	 * equals method from object class is overridden to suit the city class. This
	 * method is used to check the equality between two city objects.
	 * 
	 * returns false if the object passed is not an instance of city class returns
	 * true if the object passed refers to this object. returns true if the object
	 * passed has the same id as this object, same city name as this object, same
	 * country as this object, and same airport as this object.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof City)) {
			return false;
		}
		City other = (City) obj;
		return Objects.equals(airport, other.airport) && Objects.equals(cityId, other.cityId)
				&& Objects.equals(cityName, other.cityName) && Objects.equals(country, other.country);
	}

	/**
	 * toString method from the object class is overridden to print the details of
	 * the city object.
	 */
	@Override
	public String toString() {
		return "City [cityId=" + cityId + ", cityName=" + cityName + ", country=" + country + ", airport=" + airport
				+ "]";
	}

}
