package com.training.airline.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * This is a model class to hold flight information. It resembles the table in
 * the database under the name "alr_flights". It has private fields to represent
 * the properties of each Flight entity. It has getters and setters to access
 * and modify the private fields.
 * 
 * @author 251656
 */
@Entity(name = "alr_flights")
public class Flight implements Serializable {

	/**
	 * default serialVersionUID is used here.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This field flightId of type String represents the flight id allocated to each
	 * flight. It is a primary key, and hence it is annotated with @Id. It is mapped
	 * to a column named "flight_id" in the database.
	 */
	@Id
	@Column(name = "flight_id")
	private String flightId;

	/**
	 * This field holds the origin city id for a flight. It is coming from the City
	 * Entity. Since many flights can have same origin city, @ManyToOne is used
	 * here.
	 */
	@ManyToOne
	@JoinColumn(name = "origin_city_id", referencedColumnName = "city_id")
	private City originCity;

	/**
	 * This field holds the destination city id for a flight. It is coming from the
	 * City Entity. Since many flights can have same destination city, @ManyToOne is
	 * used here.
	 */
	@ManyToOne
	@JoinColumn(name = "dest_city_id", referencedColumnName = "city_id")
	private City destinationCity;

	/**
	 * This field represents the departure time for a flight. It is mapped to a
	 * column named "dep_time" in the database.
	 */
	@Column(name = "dep_time")
	private Time departureTime;

	/**
	 * This field represents the arrival time for a flight. It is mapped to a column
	 * named "arr_time" in the database.
	 */
	@Column(name = "arr_time")
	private Time arrivalTime;

	/**
	 * This is a bidirectional mapping, which represents the list of available
	 * flights for the given flight id. It is mapped by a variable named "flightId"
	 * in FlightAvail model class.
	 * 
	 * @JsonIgnore is used to avoid the circular dependency that would occur between
	 *             Flight and FlightAvail.
	 */
	@OneToMany(mappedBy = "flight")
	@JsonIgnore
	private List<FlightAvail> availableFlights;

	/**
	 * This is a zero argument constructor. Used to instantiate flight object.
	 */
	public Flight() {

	}

	/**
	 * This is a parameterized constructor. Takes in the following parameters to
	 * instantiate the flight object and then initialize the values with the
	 * arguments passed.
	 * 
	 * @param flightId      ---> id of the flight
	 * @param departureTime ---> departure time of the flight
	 * @param arrivalTime   ---> arrival time of the flight
	 */
	public Flight(String flightId, Time departureTime, Time arrivalTime) {
		this.flightId = flightId;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
	}

	/**
	 * This is a method used to get the flight id from a flight object
	 * 
	 * @return the flight id given for each flight.
	 */
	public String getFlightId() {
		return flightId;
	}

	/**
	 * 
	 * This is a method used to set the flight id for a flight object
	 *
	 * @param flightId The id of the flight is passed here.
	 */
	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	/**
	 * This is a method used to get the origin city object from a flight object
	 * 
	 * @return the origin city object for each flight.
	 */
	public City getOriginCity() {
		return originCity;
	}

	/**
	 * This is a method used to set the origin city object for a flight object
	 * 
	 * @param originCity The origin city object of the flight is passed here.
	 */
	public void setOriginCity(City originCity) {
		this.originCity = originCity;
	}

	/**
	 * This is a method used to get the destination city object from a flight object
	 * 
	 * @return The destination city object for each flight.
	 */
	public City getDestinationCity() {
		return destinationCity;
	}

	/**
	 * This is a method used to set the destination city object for a flight object
	 * 
	 * @param destinationCity The destination city object of the flight is passed
	 *                        here.
	 */
	public void setDestinationCity(City destinationCity) {
		this.destinationCity = destinationCity;
	}

	/**
	 * This is a method used to get the departure time from a flight object
	 * 
	 * @return The departure time of the flight.
	 */
	public Date getDepartureTime() {
		return departureTime;
	}

	/**
	 * This is a method used to set the departure time for a flight object
	 * 
	 * @param departureTime The departure time of the flight is passed here.
	 */
	public void setDepartureTime(Time departureTime) {
		this.departureTime = departureTime;
	}

	/**
	 * This is a method used to get the arrival time from a flight object
	 * 
	 * @return The arrival time of the flight.
	 */
	public Date getArrivalTime() {
		return arrivalTime;
	}

	/**
	 * This is a method used to set the arrival time for a flight object
	 * 
	 * @param arrivalTime The arrival time of the flight is passed here.
	 */
	public void setArrivalTime(Time arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	/**
	 * This is a method used to get the list of available flights for this flight id
	 * 
	 * @return List of available flights for this flight id
	 */
	public List<FlightAvail> getAvailableFlights() {
		return availableFlights;
	}

	/**
	 * This is a method used to set the list of available flights for the this
	 * flight id
	 * 
	 * @param availableFlights The list of available flights is passed here.
	 */
	public void setAvailableFlights(List<FlightAvail> availableFlights) {
		this.availableFlights = availableFlights;
	}

	/**
	 * hashCode() from the Object class is overridden with respect to the flight
	 * class. Objects.hash(Object... values) is invoked with the parameters as shown
	 * below.
	 * 
	 * @return the integer obtained from the hash method.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(arrivalTime, availableFlights, departureTime, destinationCity, flightId, originCity);
	}

	/**
	 * equals method from object class is overridden to suit the flight class. This
	 * method is used to check the equality between two flight objects.
	 * 
	 * returns false if the object passed is not an instance of flight class returns
	 * true if the object passed refers to this object. returns true if the object
	 * passed has the same id as this object, same origin city as this object, same
	 * destination city as this object, same departure time as this object, same
	 * arrival time as this object, same available flights as this object.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Flight)) {
			return false;
		}
		Flight other = (Flight) obj;
		return Objects.equals(arrivalTime, other.arrivalTime)
				&& Objects.equals(availableFlights, other.availableFlights)
				&& Objects.equals(departureTime, other.departureTime)
				&& Objects.equals(destinationCity, other.destinationCity) && Objects.equals(flightId, other.flightId)
				&& Objects.equals(originCity, other.originCity);
	}

	/**
	 * toString method from the object class is overridden to print the details of
	 * the flight object.
	 */
	@Override
	public String toString() {
		return "Flights [flightId=" + flightId + ", originCity=" + originCity + ", destinationCity=" + destinationCity
				+ ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime + "]";
	}

}
