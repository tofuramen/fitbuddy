package com.fitBuddyGuy.fitBuddyApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class FitBuddyAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FitBuddyAppApplication.class, args);
	}

}
