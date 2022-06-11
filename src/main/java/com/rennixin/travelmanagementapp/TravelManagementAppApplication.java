package com.rennixin.travelmanagementapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class TravelManagementAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelManagementAppApplication.class, args);
	}

}
