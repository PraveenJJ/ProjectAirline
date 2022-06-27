package com.training.airline.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * This is a model class to hold user information. It resembles the table in the
 * database under the name "alr_user". It has private fields to represent the
 * properties of each user entity. It has getters and setters to access and
 * modify the private fields. A stored procedure is defined for the user in the
 * database which is mapped to this entity class
 * using @NamedStoredProcedureQuery
 * 
 * @author Praveen J
 */
@Entity(name = "alr_user")
@NamedStoredProcedureQueries({
		@NamedStoredProcedureQuery(name = "user_save", procedureName = "P_user_sav", parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "userName", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "password", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "creditCardNo", type = String.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "creditCardType", type = Character.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "creditCardMonth", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "creditCardYear", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "age", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name = "outParam", type = String.class) }) })
public class User implements Serializable {

	/**
	 * default serialVersionUID is used here.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This field userId of type Integer represents the user id allocated to each
	 * user. It is a primary key, and hence it is annotated with @Id. It is to be
	 * auto-incremented by the database, and so the GenerationType for Id is
	 * IDENTITY. It is mapped to a column named "user_id" in the database.
	 */
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;

	/**
	 * This field name of type String represents the name given for a user. It is
	 * mapped to a column named "name" in the database.
	 */
	@Column(name = "name")
	private String name;

	/**
	 * This field password of type String represents the password for a user. It is
	 * mapped to a column named "password" in the database.
	 */
	@Column(name = "password")
	private String password;

	/**
	 * This field creditCardNumber of type String represents the credit card number
	 * for a user. It is mapped to a column named "creditcardno" in the database.
	 */
	@Column(name = "creditcardno")
	private String creditCardNumber;

	/**
	 * This field creditCardType of type Character represents the credit card type
	 * for a user. It is mapped to a column named "creditcardtype" in the database.
	 */
	@Column(name = "creditcardtype")
	private Character creditCardType;

	/**
	 * This field creditCardMonth of type Integer represents the credit card expiry
	 * month for a user. It is mapped to a column named "creditcardmonth" in the
	 * database.
	 */
	@Column(name = "creditcardmonth")
	private Integer creditCardMonth;

	/**
	 * This field creditcardyear of type Integer represents the credit card expiry
	 * year for a user. It is mapped to a column named "creditcardyear" in the
	 * database.
	 */
	@Column(name = "creditcardyear")
	private Integer creditCardYear;

	/**
	 * This field age of type Integer represents the age for a user. It is mapped to
	 * a column named "age" in the database.
	 */
	@Column(name = "age")
	private Integer age;

	/**
	 * This is a bidirectional mapping, which represents the list of bookings that
	 * has user id as this user. It is mapped by a variable named "user" in Booking
	 * model class.
	 * 
	 * @JsonIgnore is used to avoid the circular dependency that would occur between
	 *             User and Booking.
	 */
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Booking> bookedFlights;

	/**
	 * This is a zero argument constructor. Used to instantiate user object.
	 */
	public User() {

	}

	/**
	 * This is a parameterized constructor. Takes in the following parameters to
	 * instantiate the user object and then initialize the values with the arguments
	 * passed.
	 * 
	 * @param name             ---> name of the user
	 * @param password         ---> password of the user
	 * @param creditCardNumber ---> credit card number of the user
	 * @param creditCardType   ---> credit card type of the user
	 * @param creditCardMonth  ---> credit card expiry month of the user
	 * @param creditCardYear   ---> credit card expiry year of the user
	 * @param age              ---> age of the user
	 */
	public User(String name, String password, String creditCardNumber, Character creditCardType,
			Integer creditCardMonth, Integer creditCardYear, Integer age) {
		this.name = name;
		this.password = password;
		this.creditCardNumber = creditCardNumber;
		this.creditCardType = creditCardType;
		this.creditCardMonth = creditCardMonth;
		this.creditCardYear = creditCardYear;
		this.age = age;
	}

	/**
	 * This is a method used to get the user id from a user object
	 * 
	 * @return the user id given for each user.
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * This is a method used to set the user id for a user object
	 * 
	 * @param userId The id of the user is passed here.
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * This is a method used to get the user name from a user object
	 * 
	 * @return the user name given for each user.
	 */
	public String getName() {
		return name;
	}

	/**
	 * This is a method used to set the user name for a user object
	 * 
	 * @param name The name of the user is passed here.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This is a method used to get the user password from a user object
	 * 
	 * @return the user password given for each user.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * This is a method used to set the user password for a user object
	 * 
	 * @param password The password of the user is passed here.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * This is a method used to get the credit card number from a user object
	 * 
	 * @return the credit card number given for each user.
	 */
	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	/**
	 * This is a method used to set the credit card number for a user object
	 * 
	 * @param creditCardNumber The credit card number of the user is passed here.
	 */
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	/**
	 * This is a method used to get the credit card type from a user object
	 * 
	 * @return the credit card type given for each user.
	 */
	public Character getCreditCardType() {
		return creditCardType;
	}

	/**
	 * This is a method used to set the credit card type for a user object
	 * 
	 * @param creditCardType The credit card type of the user is passed here.
	 */
	public void setCreditCardType(Character creditCardType) {
		this.creditCardType = creditCardType;
	}

	/**
	 * This is a method used to get the credit card month from a user object
	 * 
	 * @return the credit card month given for each user.
	 */
	public Integer getCreditCardMonth() {
		return creditCardMonth;
	}

	/**
	 * This is a method used to set the credit card month for a user object
	 * 
	 * @param creditCardMonth The credit card month of the user is passed here.
	 */
	public void setCreditCardMonth(Integer creditCardMonth) {
		this.creditCardMonth = creditCardMonth;
	}

	/**
	 * This is a method used to get the credit card year from a user object
	 * 
	 * @return the credit card month year for each user.
	 */
	public Integer getCreditCardYear() {
		return creditCardYear;
	}

	/**
	 * This is a method used to set the credit card year for a user object
	 * 
	 * @param creditCardYear The credit card year of the user is passed here.
	 */
	public void setCreditCardYear(Integer creditCardYear) {
		this.creditCardYear = creditCardYear;
	}

	/**
	 * This is a method used to get the age from a user object
	 * 
	 * @return the age for each user.
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * This is a method used to set the age for a user object
	 * 
	 * @param age The age of the user is passed here.
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * This is a method used to get the list of bookings from a user object
	 * 
	 * @return list of bookings which has this user id
	 */
	public List<Booking> getBookedFlights() {
		return bookedFlights;
	}

	/**
	 * This is a method used to set the list of bookings for a user object
	 * 
	 * @param bookedFlights The list of bookings is passed here.
	 */
	public void setBookedFlights(List<Booking> bookedFlights) {
		this.bookedFlights = bookedFlights;
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
		return Objects.hash(age, bookedFlights, creditCardMonth, creditCardNumber, creditCardType, creditCardYear, name,
				password, userId);
	}

	/**
	 * equals method from object class is overridden to suit the user class. This
	 * method is used to check the equality between two user objects.
	 * 
	 * returns false if the object passed is not an instance of user class. returns
	 * true if the object passed refers to this object. returns true if the object
	 * passed has the same id as this object, same password as this object, same
	 * name as this object, same age as this object, same credit card number as this
	 * object, same credit card month as this object, same credit card year as this
	 * object, same credit card type as this object, same booked flights as this
	 * object, and same airport as this object.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		User other = (User) obj;
		return Objects.equals(age, other.age) && Objects.equals(bookedFlights, other.bookedFlights)
				&& Objects.equals(creditCardMonth, other.creditCardMonth)
				&& Objects.equals(creditCardNumber, other.creditCardNumber)
				&& Objects.equals(creditCardType, other.creditCardType)
				&& Objects.equals(creditCardYear, other.creditCardYear) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password) && Objects.equals(userId, other.userId);
	}

	/**
	 * toString method from the object class is overridden to print the details of
	 * the user object.
	 */
	@Override
	public String toString() {
		return "Users [userId=" + userId + ", name=" + name + ", age=" + age + "]";
	}

}
