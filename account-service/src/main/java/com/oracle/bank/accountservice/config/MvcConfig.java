package com.oracle.bank.accountservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Joby Wilson Mathews
 *
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
	
	  @Override
	  public void configureViewResolvers(ViewResolverRegistry registry) {
	    registry.jsp().prefix("/WEB-INF/views/").suffix(".html");
	  }

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
	}

}