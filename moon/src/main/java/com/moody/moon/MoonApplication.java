package com.moody.moon;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class MoonApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoonApplication.class, args);
	}
	
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver=new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}
	// if it's defined in application.properties file spring boot don't need this code
	/*@Bean
	public ResourceBundleMessageSource messageSource() {
		
		ResourceBundleMessageSource messageSource=new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}*/

}
