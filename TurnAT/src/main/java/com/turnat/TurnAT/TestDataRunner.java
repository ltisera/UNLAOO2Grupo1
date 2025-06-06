package com.turnat.TurnAT;

import org.springframework.boot.CommandLineRunner;

import org.springframework.stereotype.Component;
import com.turnat.TurnAT.models.entities.Estado;
import com.turnat.TurnAT.models.entities.Direccion;
import com.turnat.TurnAT.models.entities.Cliente;
import com.turnat.TurnAT.repositories.IClienteRepository;
import com.turnat.TurnAT.repositories.IEstadoRepository;
import com.turnat.TurnAT.services.IClienteService;

@Component

public class TestDataRunner implements CommandLineRunner {
	
	private final IClienteRepository repoC;
	private final IClienteService servC;
	
	public TestDataRunner(IClienteRepository repoC, IClienteService servC) {
        this.repoC = repoC;
        this.servC = servC;
    }
	
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Ejecutando prueba rápida con datos hardcodeados...");

        // Ejemplo: Instanciando objetos Cliente y Empleado
        
        Direccion dir = new Direccion("glew23", "fffAlguna" , 7888);
        Cliente cli = new Cliente("Nico", "mipasasdfsa", "Carpinchjoasdff", 33111312, "m@mail", dir, "2333");
        
        
        // Mostrar datos por consola (o usar repositorios si ya los tenés)
        System.out.println("Cliente:" + cli.toString());
        
        servC.agregar(cli);
        
        // Aquí podrías llamar a repositorios para guardar estos datos si los configuraste.
    }
}