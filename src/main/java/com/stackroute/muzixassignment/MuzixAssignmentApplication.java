package com.stackroute.muzixassignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //annotation is used to mark a configuration class that declares one or more
// @Bean methods and also triggers auto-configuration and component scanning
public class MuzixAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(MuzixAssignmentApplication.class, args);
	}

}
