package com.quickbite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class QuickbiteFoodDeliveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuickbiteFoodDeliveryApplication.class, args);
	}
}
