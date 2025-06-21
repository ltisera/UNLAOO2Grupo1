package com.turnat.TurnAT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class TurnAtApplication {

	public static void main(String[] args) {
		SpringApplication.run(TurnAtApplication.class, args);
		System.out.println("MAIL_USERNAME: " + System.getenv("MAIL_USERNAME"));
		System.out.println("MAIL_PASSWORD: " + System.getenv("MAIL_PASSWORD"));

	}

}


