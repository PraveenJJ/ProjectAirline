package com.ntt.airline.repositorytest;

import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.training.airline.model.City;
import com.training.airline.repository.CityRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CityRepository.class)
public class CityRepositoryTest {
	
	@Autowired
	private CityRepository cityRepository;

	@Test
	public void findAllCities() {
		
		List<City> list = cityRepository.findAll();
		
		System.out.println(list);
		for (Iterator<City> iterator = list.iterator(); iterator.hasNext();) {
			City city = iterator.next();
			System.out.println(city);
		}
		
		
	}
	
}
