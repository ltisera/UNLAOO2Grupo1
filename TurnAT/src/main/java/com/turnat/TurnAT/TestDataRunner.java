package com.turnat.TurnAT;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.turnat.TurnAT.models.entities.Estado;
import com.turnat.TurnAT.models.entities.Direccion;
import com.turnat.TurnAT.models.entities.Cliente;
import com.turnat.TurnAT.repositories.IClienteRepository;
import com.turnat.TurnAT.repositories.IEstadoRepository;
@Component

public class TestDataRunner implements CommandLineRunner {
	
	private final IClienteRepository repoC;
	
	public TestDataRunner(IClienteRepository repoC) {
        this.repoC = repoC;
    }
	
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Ejecutando prueba rápida con datos hardcodeados...");

        // Ejemplo: Instanciando objetos Cliente y Empleado
        
        Direccion dir = new Direccion("glew", "Alguna" , 7888);
        Cliente cli = new Cliente("Nico", "mipass", "Carpinchjo", 3332423, "m@mail", dir, "2333");
        
        
        // Mostrar datos por consola (o usar repositorios si ya los tenés)
        System.out.println("Cliente:" + cli.toString());
        
        repoC.save(cli);
        
        // Aquí podrías llamar a repositorios para guardar estos datos si los configuraste.
    }
}