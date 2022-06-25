package com.training.airline.config;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * This is a class where all the configuration beans are written, which supports
 * the execution of the repository related functionalities.
 * 
 * @author 251656
 */
@Configuration
@EnableJpaRepositories(basePackages = { "com.training.airline.repository" })
@EnableTransactionManagement
public class JpaConfig {

	/**
	 * This method is annotated with @Bean, so that whenever an instance of
	 * entityManager is required within the application, then this bean will be
	 * injected.
	 * 
	 * @return LocalEntityManagerFactoryBean
	 */
	@Bean
	public LocalEntityManagerFactoryBean entityManagerFactory() {

		// creating an instance of LocalEntityManagerFactoryBean
		LocalEntityManagerFactoryBean entityManagerFactory = new LocalEntityManagerFactoryBean();

		// setting the persistance unit name for the entity manager factory bean
		entityManagerFactory.setPersistenceUnitName("ProjectAirlineUnit");

		// returning the entity manager factory bean
		return entityManagerFactory;

	}

	/**
	 * This method is annotated with @Bean, so that whenever an instance of
	 * JpaTransactionManager is required within the application, then this bean will
	 * be injected.
	 * 
	 * @param entityManagerFactory
	 * @return JpaTransationManager
	 */
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {

		// creating an instance of JpaTransactionManager
		JpaTransactionManager transactionManager = new JpaTransactionManager();

		// setting the entity manager factory to the jpa transaction manager
		transactionManager.setEntityManagerFactory(entityManagerFactory);

		// returning the jpa transaction manager
		return transactionManager;

	}

}
