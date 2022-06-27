package com.training.airline.repository;

import java.sql.SQLException;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.training.airline.model.User;

/**
 * This is a repository interface for User. It has all the repository methods
 * related to User Entity. All the repository related operations with the user
 * are handled by this repository interface.
 * 
 * @author Praveen J
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	/**
	 * This method is used to find the user object for the given user id and
	 * password, from the database and returns back the user object to the service
	 * class. This method uses a native query which is specified in the jpa named
	 * properties file.
	 * 
	 * @param userId
	 * @param password
	 * @return user object
	 * @throws SQLException
	 */
	@Query(nativeQuery = true)
	public User validateUserRepository(@Param("userId") Integer userId, @Param("password") String password)
			throws SQLException;

	/**
	 * This method is used to save the user details into the database using all the
	 * given details and return back the user id to the service class. This is a
	 * named stored procedure, it's parameters are specified in the entity class.
	 * 
	 * @param userName
	 * @param password
	 * @param creditCardNo
	 * @param creditCardType
	 * @param creditCardMonth
	 * @param creditCardYear
	 * @param age
	 * @return user id
	 * @throws SQLException
	 */
	@Procedure(name = "user_save")
	public String registerUserRepository(@Param("userName") String userName, @Param("password") String password,
			@Param("creditCardNo") String creditCardNo, @Param("creditCardType") Character creditCardType,
			@Param("creditCardMonth") Integer creditCardMonth, @Param("creditCardYear") Integer creditCardYear,
			@Param("age") Integer age) throws SQLException;

}
