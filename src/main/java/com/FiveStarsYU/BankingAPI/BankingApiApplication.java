package com.FiveStarsYU.BankingAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class BankingApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(BankingApiApplication.class, args);
	}

}
