package com.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class SpringBootSecurityBankAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityBankAppApplication.class, args);
	}

}
