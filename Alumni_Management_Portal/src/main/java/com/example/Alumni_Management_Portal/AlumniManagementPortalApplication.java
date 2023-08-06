package com.example.Alumni_Management_Portal;

import com.example.Alumni_Management_Portal.aspects.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
public class AlumniManagementPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlumniManagementPortalApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public Logger logger() {
		return new Logger();
	}
}
