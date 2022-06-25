package com.ntt.airline.repositorytest;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.training.airline.model.User;
import com.training.airline.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {
	
	@Spy
	private UserRepository userRepository;

	@Test
	public void validateUserRepositoryTest() {
		
		Optional<User> optional = userRepository.findById(1);
		System.out.println(optional);
		
	}
	
}
