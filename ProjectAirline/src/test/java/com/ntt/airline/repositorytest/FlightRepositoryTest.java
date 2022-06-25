package com.ntt.airline.repositorytest;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.training.airline.model.Flight;
import com.training.airline.repository.FlightRepository;

@ExtendWith(MockitoExtension.class)
public class FlightRepositoryTest {
	
	@Spy
	private FlightRepository flightRepository;
	
	@Test
	public void getFlightsByRouteRepositoryTest() {
		
		List<Flight> flightsByRouteRepository;
		try {
			flightsByRouteRepository = flightRepository.getFlightsByRouteRepository(1, 2);
			System.out.println(flightsByRouteRepository);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
