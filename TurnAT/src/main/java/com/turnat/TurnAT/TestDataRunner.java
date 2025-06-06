/*package com.turnat.TurnAT;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.turnat.TurnAT.models.entities.Cliente;
import com.turnat.TurnAT.models.entities.Direccion;
import com.turnat.TurnAT.repositories.IPersonaRepository;
@Component

public class TestDataRunner implements CommandLineRunner {
	
	private final IPersonaRepository repoP;
	
	public TestDataRunner(IPersonaRepository repoP) {
        this.repoP = repoP;
    }
	
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Ejecutando prueba rápida con datos hardcodeados...");

        // Ejemplo: Instanciando objetos Cliente y Empleado
        
        Direccion dir = new Direccion("glew", "Alguna" , 7888);
        Cliente cliente = new Cliente("juan", "poll", 2334233, "arro@arroba.com", dir, "+54 11 1111 1111");
        
        
        // Mostrar datos por consola (o usar repositorios si ya los tenés)
        System.out.println("Cliente: " + cliente.getNombre() + ", Email: " + cliente.getEmail());
        
        repoP.save(cliente);
        
        // Aquí podrías llamar a repositorios para guardar estos datos si los configuraste.
    }
}*/