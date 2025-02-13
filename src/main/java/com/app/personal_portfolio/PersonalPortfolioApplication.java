package com.app.personal_portfolio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class PersonalPortfolioApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalPortfolioApplication.class, args);
	}
	
}
