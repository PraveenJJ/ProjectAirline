package com.training.airline.repository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.training.airline.model.City;

/**
 * This is a repository interface for City. It has all the repository methods
 * related to City Entity. All the repository related operations with the city
 * are handled by this repository interface.
 * 
 * @author 251656
 */
@Repository
public interface CityRepository extends CrudRepository<City, Integer> {

	/**
	 * This method is specified within the CrudRepository, it finds all the cities
	 * from the database and returns back the list of cities.
	 * 
	 * @return list of cities
	 */
	List<City> findAll();

	/**
	 * This method is used to fetch the city object using the city name, it works on
	 * derived query, and it returns back the city found.
	 * 
	 * @param cityName
	 * @return city object
	 * @throws SQLException
	 */
	City findByCityName(String cityName) throws SQLException;

}
