package com.turnat;
import com.turnat.test.agregarPersona;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class TurnAtApplication {

	public static void main(String[] args) {
		SpringApplication.run(agregarPersona.class, args);
		
		System.out.println("Vamo arriba habemus endpoint");
	}

}
