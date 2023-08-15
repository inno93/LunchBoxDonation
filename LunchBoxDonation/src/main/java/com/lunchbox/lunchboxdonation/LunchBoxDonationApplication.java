package com.lunchbox.lunchboxdonation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LunchBoxDonationApplication {

	public static void main(String[] args) {
		SpringApplication.run(LunchBoxDonationApplication.class, args);
	}

}
