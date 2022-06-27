package com.training.airline.dto;

import java.io.Serializable;
import java.util.List;

/**
 * This is a Dto class to hold user information. It has private fields to
 * represent the properties of each User entity. It has getters and setters to
 * access and modify the private fields.
 * 
 * @author Praveen J
 */
public class UserDto implements Serializable {

	/**
	 * default serialVersionUID is used here.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This field userId of type Integer represents the user id allocated to each
	 * user.
	 */
	private Integer userId;

	/**
	 * This field name of type String represents the name given for a user.
	 */
	private String name;

	/**
	 * This field password of type String represents the password for a user.
	 */
	private String password;

	/**
	 * This field creditCardNumber of type String represents the credit card number
	 * for a user.
	 */
	private String creditCardNumber;

	/**
	 * This field creditCardType of type Character represents the credit card type
	 * for a user.
	 */
	private Character creditCardType;

	/**
	 * This field creditCardMonth of type Integer represents the credit card expiry
	 * month for a user.
	 */
	private Integer creditCardMonth;

	/**
	 * This field creditcardyear of type Integer represents the credit card expiry
	 * year for a user.
	 */
	private Integer creditCardYear;

	/**
	 * This field age of type Integer represents the age for a user.
	 */
	private Integer age;

	/**
	 * This field which represents the list of bookings dto that has user id as this
	 * user.
	 */
	private List<BookingDto> bookedFlights;

	/**
	 * This is a zero argument constructor. Used to instantiate user dto object.
	 */
	public UserDto() {

	}

	/**
	 * This is a parameterized constructor. Takes in the following parameters to
	 * instantiate the user dto object and then initialize the values with the arguments
	 * passed.
	 * 
	 * @param userId		   ---> id of the user
	 * @param name             ---> name of the user
	 * @param password         ---> password of the user
	 * @param creditCardNumber ---> credit card number of the user
	 * @param creditCardType   ---> credit card type of the user
	 * @param creditCardMonth  ---> credit card expiry month of the user
	 * @param creditCardYear   ---> credit card expiry year of the user
	 * @param age              ---> age of the user
	 */
	public UserDto(Integer userId, String name, String password, String creditCardNumber, Character creditCardType,
			Integer creditCardMonth, Integer creditCardYear, Integer age) {
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.creditCardNumber = creditCardNumber;
		this.creditCardType = creditCardType;
		this.creditCardMonth = creditCardMonth;
		this.creditCardYear = creditCardYear;
		this.age = age;
	}

	/**
	 * This is a method used to get the user id from a user dto object
	 * 
	 * @return the user id given for each user.
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * This is a method used to set the user id for a user dto object
	 * 
	 * @param userId The id of the user is passed here.
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * This is a method used to get the user name from a user dto object
	 * 
	 * @return the user name given for each user.
	 */
	public String getName() {
		return name;
	}

	/**
	 * This is a method used to set the user name for a user dto object
	 * 
	 * @param name The name of the user is passed here.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This is a method used to get the user password from a user dto object
	 * 
	 * @return the user password given for each user.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * This is a method used to set the user password for a user dto object
	 * 
	 * @param password The password of the user is passed here.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * This is a method used to get the credit card number from a user dto object
	 * 
	 * @return the credit card number given for each user.
	 */
	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	/**
	 * This is a method used to set the credit card number for a user dto object
	 * 
	 * @param creditCardNumber The credit card number of the user is passed here.
	 */
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	/**
	 * This is a method used to get the credit card type from a user dto object
	 * 
	 * @return the credit card type given for each user.
	 */
	public Character getCreditCardType() {
		return creditCardType;
	}

	/**
	 * This is a method used to set the credit card type for a user dto object
	 * 
	 * @param creditCardType The credit card type of the user is passed here.
	 */
	public void setCreditCardType(Character creditCardType) {
		this.creditCardType = creditCardType;
	}

	/**
	 * This is a method used to get the credit card month from a user dto object
	 * 
	 * @return the credit card month given for each user.
	 */
	public Integer getCreditCardMonth() {
		return creditCardMonth;
	}

	/**
	 * This is a method used to set the credit card month for a user dto object
	 * 
	 * @param creditCardMonth The credit card month of the user is passed here.
	 */
	public void setCreditCardMonth(Integer creditCardMonth) {
		this.creditCardMonth = creditCardMonth;
	}

	/**
	 * This is a method used to get the credit card year from a user dto object
	 * 
	 * @return the credit card month year for each user.
	 */
	public Integer getCreditCardYear() {
		return creditCardYear;
	}

	/**
	 * This is a method used to set the credit card year for a user dto object
	 * 
	 * @param creditCardYear The credit card year of the user is passed here.
	 */
	public void setCreditCardYear(Integer creditCardYear) {
		this.creditCardYear = creditCardYear;
	}

	/**
	 * This is a method used to get the age from a user dto object
	 * 
	 * @return the age for each user.
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * This is a method used to set the age for a user dto object
	 * 
	 * @param age The age of the user is passed here.
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * This is a method used to get the list of bookings dto from a user dto object
	 * 
	 * @return list of bookings dto which has this user id
	 */
	public List<BookingDto> getBookedFlights() {
		return bookedFlights;
	}

	/**
	 * This is a method used to set the list of bookings dto for a user dto object
	 * 
	 * @param bookedFlights The list of bookings dto is passed here.
	 */
	public void setBookedFlights(List<BookingDto> bookedFlights) {
		this.bookedFlights = bookedFlights;
	}

}
