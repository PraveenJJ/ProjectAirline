package com.training.airline.controller;

import org.springframework.web.servlet.View;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * This class RESTConfiguration provides the configuration for the format in
 * which the view should be rendered as response to the requests.
 * 
 * @author Praveen J
 */
@Configuration
public class RESTConfiguration {

	/**
	 * This method is annotated with @Bean, so that whenever an instance of view is
	 * required within the application, then this bean will be injected.
	 * 
	 * @return View
	 */
	@Bean
	public View jsonTemplate() {

		// instance of MappingJackson2JsonView is created here
		MappingJackson2JsonView view = new MappingJackson2JsonView();

		// pretty print for the view is set to true here.
		view.setPrettyPrint(true);

		// returning the view
		return view;
	}

	/**
	 * This method is annotated with @Bean, so that whenever an instance of
	 * ViewResolver is required within the application, then this bean will be
	 * injected.
	 * 
	 * @return ViewResolver
	 */
	@Bean
	public ViewResolver viewResolver() {

		// returning new BeanNameViewResolver
		return new BeanNameViewResolver();

	}

}
