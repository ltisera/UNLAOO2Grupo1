package com.turnat.test;

import com.turnat.entities.Persona;
import com.turnat.entities.Direccion;

import org.springframework.beans.factory.annotation.Autowired;

import com.turnat.entities.Cliente;

import com.turnat.repositories.iClienteRepository;


public class agregarPersona {
	
	@Autowired
    private iClienteRepository repoCliente;
	public void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		Direccion dir = new Direccion("Lomas", "Te Afano el nokia", 1100);
		Cliente cpesado = new Cliente("daniel", "caruso" , 45124996, "karatedan@gm.com", dir);
		Cliente g = repoCliente.save(cpesado);
		System.out.println("Soy el santiiiii");
	
	}

}
