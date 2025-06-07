package com.turnat.TurnAT;

import org.springframework.boot.CommandLineRunner;

import org.springframework.stereotype.Component;
import com.turnat.TurnAT.models.entities.Estado;
import com.turnat.TurnAT.models.entities.Sucursal;
import com.turnat.TurnAT.models.entities.Direccion;
import com.turnat.TurnAT.models.entities.Cliente;
import com.turnat.TurnAT.repositories.IClienteRepository;
import com.turnat.TurnAT.repositories.IEstadoRepository;
import com.turnat.TurnAT.repositories.ISucursalRepository;
import com.turnat.TurnAT.services.interfaces.IClienteService;
import com.turnat.TurnAT.services.interfaces.ISucursalService;

@Component

public class TestDataRunner implements CommandLineRunner {
	
	private final ISucursalRepository repoS;
	private final ISucursalService servS;
	
	public TestDataRunner(ISucursalRepository repoS, ISucursalService servS) {
        this.repoS = repoS;
        this.servS = servS;
    }
	
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Ejecutando prueba rápida con datos hardcodeados...");

       
        Direccion dir = new Direccion("Temperley", "Juncal" , 100);
        Sucursal suc = new Sucursal("Juncal", "+54 2211 2222", dir);
        
        
        // Mostrar datos por consola (o usar repositorios si ya los tenés)
        System.out.println("Sucursal:" + suc.toString());
        
        servS.agregar(suc);
        
        // Aquí podrías llamar a repositorios para guardar estos datos si los configuraste.
    }
}