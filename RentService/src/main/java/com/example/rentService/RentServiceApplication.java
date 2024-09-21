package com.example.rentService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentServiceApplication.class, args);
	}

}
