package com.stackroute.muzix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication // annotation is used to mark a configuration class that declares one or more
// @Bean methods and also triggers auto-configuration and component scanning
@Configuration //@Configuration to enable Java-based configuration
@EnableAutoConfiguration //@EnableAutoConfiguration to enable Spring Boot's auto-configuration feature.
@ComponentScan //@ComponentScan to enable component scanning.
public class MuzixApplication
{

	public static void main(String[] args)
	{

		SpringApplication.run(MuzixApplication.class, args);
	}

}
