package com.turnat.TurnAT;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.turnat.TurnAT.models.entities.Estado;
import com.turnat.TurnAT.models.entities.Direccion;
import com.turnat.TurnAT.repositories.IEstadoRepository;
@Component

public class TestDataRunner implements CommandLineRunner {
	
	private final IEstadoRepository repoE;
	
	public TestDataRunner(IEstadoRepository repoE) {
        this.repoE = repoE;
    }
	
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Ejecutando prueba rápida con datos hardcodeados...");

        // Ejemplo: Instanciando objetos Cliente y Empleado
        
        //Direccion dir = new Direccion("glew", "Alguna" , 7888);
        Estado est = new Estado("juan1111 1111");
        
        
        // Mostrar datos por consola (o usar repositorios si ya los tenés)
        System.out.println("Estado:" + est.getDescripcion());
        
        repoE.save(est);
        
        // Aquí podrías llamar a repositorios para guardar estos datos si los configuraste.
    }
}