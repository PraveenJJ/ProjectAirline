package com.training.airline.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;

/**
 * This is a model class to hold passenger information. It resembles the table
 * in the database under the name "alr_passengers". It has private fields to
 * represent the properties of each passenger entity. It has getters and setters
 * to access and modify the private fields. A stored procedure is defined for
 * the passenger in the database which is mapped to this entity class
 * using @NamedStoredProcedureQuery
 * 
 * @author Praveen J
 */
@Entity(name = "alr_passengers")
@NamedStoredProcedureQueries({
		@NamedStoredProcedureQuery(name = "passenger_save", procedureName = "P_passenger_sav", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "referenceId", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "name", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "age", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name = "outParam2", type = String.class) }) })
public class Passenger implements Serializable {

	/**
	 * default serialVersionUID is used here.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This field referenceId of type Booking represents the Booking object. It is
	 * used to obtain a relation between Passenger and Booking using the field
	 * reference id as a join. It is mapped to a column named "reference_id" in the
	 * database. Since many passengers are available with same booking reference
	 * id, @ManyToOne is used here.
	 */
	@ManyToOne
	@JoinColumn(name = "reference_id", referencedColumnName = "reference_id")
	private Booking booking; 

	/**
	 * This field ticketNumber of type Integer represents the ticket number
	 * allocated to each passenger. It is a primary key, and hence it is annotated
	 * with @Id. It is to be auto-incremented by the database, and so the
	 * GenerationType for Id is IDENTITY. It is mapped to a column named
	 * "ticketnumber" in the database.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ticketnumber")
	private Integer ticketNumber;

	/**
	 * This field passengerName of type String represents the name given for a
	 * passenger. It is mapped to a column named "pass_name" in the database.
	 */
	@Column(name = "pass_name")
	private String passengerName;

	/**
	 * This field age of type Integer represents the age for a passenger. It is
	 * mapped to a column named "age" in the database.
	 */
	@Column(name = "age")
	private Integer age;

	/**
	 * This field status of type String represents the booking status given for a
	 * passenger. It is mapped to a column named "status" in the database. It can
	 * have length upto 2.
	 */
	@Column(name = "status", length = 2)
	private String status;

	/**
	 * This is a zero argument constructor. Used to instantiate city object.
	 */
	public Passenger() {

	}

	/**
	 * This is a parameterized constructor. Takes in the following parameters to
	 * instantiate the passenger object and then initialize the values with the
	 * arguments passed.
	 * 
	 * @param passengerName ---> name of the passenger
	 * @param age           ---> age of the passenger
	 * @param status        ---> status of ticket of the passenger
	 */
	public Passenger(String passengerName, Integer age, String status) {
		this.passengerName = passengerName;
		this.age = age;
		this.status = status;
	}

	/**
	 * This is a method used to get the booking object from a passenger object
	 * 
	 * @return the booking object given for each passenger.
	 */
	public Booking getBooking() {
		return booking;
	}

	/**
	 * This is a method used to set the booking object for a passenger object
	 * 
	 * @param booking The booking object is passed here.
	 */
	public void setBooking(Booking booking) { 
		this.booking = booking;
	}

	/**
	 * This is a method used to get the ticket number from a passenger object
	 * 
	 * @return the ticket number given for each passenger.
	 */
	public Integer getTicketNumber() {
		return ticketNumber;
	}

	/**
	 * This is a method used to set the ticket number for a passenger object
	 * 
	 * @param ticketNumber The ticket number is passed here.
	 */
	public void setTicketNumber(Integer ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	/**
	 * This is a method used to get the passenger name from a passenger object
	 * 
	 * @return the passenger name given for each passenger.
	 */
	public String getPassengerName() {
		return passengerName;
	}

	/**
	 * This is a method used to set the passenger name for a passenger object
	 * 
	 * @param passengerName The passenger name is passed here.
	 */
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	/**
	 * This is a method used to get the age from a passenger object
	 * 
	 * @return the age for each passenger.
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * This is a method used to set the age for a passenger object
	 * 
	 * @param age The age of the passenger is passed here.
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * This is a method used to get the status of the ticket from a passenger object
	 * 
	 * @return the status of the ticket for each passenger.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * This is a method used to set the status of the ticket for a passenger object
	 * 
	 * @param status The status of the ticket of the passenger is passed here.
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * hashCode() from the Object class is overridden with respect to the passenger
	 * class. Objects.hash(Object... values) is invoked with the parameters as shown
	 * below.
	 * 
	 * @return the integer obtained from the hash method.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(age, passengerName, booking, status, ticketNumber);
	}

	/**
	 * equals method from object class is overridden to suit the passenger class.
	 * This method is used to check the equality between two passenger objects.
	 * 
	 * returns false if the object passed is not an instance of passenger class.
	 * returns true if the object passed refers to this object. returns true if the
	 * object passed has the same reference id as this object, same ticket number as
	 * this object, same passenger name as this object, same age as this object, and
	 * same status as this object.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Passenger)) {
			return false;
		}
		Passenger other = (Passenger) obj;
		return Objects.equals(age, other.age) && Objects.equals(passengerName, other.passengerName)
				&& Objects.equals(booking, other.booking) && Objects.equals(status, other.status)
				&& Objects.equals(ticketNumber, other.ticketNumber);
	}

	/**
	 * toString method from the object class is overridden to print the details of
	 * the passenger object.
	 */
	@Override
	public String toString() {
		return "Passengers [booking=" + booking + ", ticketNumber=" + ticketNumber + ", passengerName="
				+ passengerName + ", age=" + age + ", status=" + status + "]";
	}
}
