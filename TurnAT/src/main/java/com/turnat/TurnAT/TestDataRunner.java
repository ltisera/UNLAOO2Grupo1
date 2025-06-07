package com.turnat.TurnAT;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;

import org.springframework.stereotype.Component;
import com.turnat.TurnAT.models.entities.Estado;
import com.turnat.TurnAT.models.entities.Servicio;
import com.turnat.TurnAT.models.entities.Sucursal;
import com.turnat.TurnAT.models.entities.Direccion;
import com.turnat.TurnAT.models.entities.Disponible;
import com.turnat.TurnAT.models.entities.Empleado;
import com.turnat.TurnAT.models.entities.Cliente;
import com.turnat.TurnAT.repositories.IClienteRepository;
import com.turnat.TurnAT.repositories.IEmpleadoRepository;
import com.turnat.TurnAT.repositories.IEstadoRepository;
import com.turnat.TurnAT.repositories.IServicioRepository;
import com.turnat.TurnAT.repositories.ISucursalRepository;
import com.turnat.TurnAT.services.interfaces.IClienteService;
import com.turnat.TurnAT.services.interfaces.IEmpleadoService;
import com.turnat.TurnAT.services.interfaces.IServicioService;
import com.turnat.TurnAT.services.interfaces.ISucursalService;

@Component

public class TestDataRunner implements CommandLineRunner {
	
	
	// testeo de sucursal
	
	/*private final ISucursalRepository repoS;
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
    }*/
	
	
	//------------------------------------------------
	//| TESTEO DE SEERVCIO Y ASIGNANDOLE UNA SUCURSAL  |
	//------------------------------------------------	
	/*private final IServicioRepository repoS;
	private final IServicioService servS;
	private final ISucursalRepository repoSuc;
	private final ISucursalService servSuc;
	public TestDataRunner(IServicioRepository repoS, IServicioService servS, ISucursalRepository repoSuc, ISucursalService servSuc) {
        this.repoS = repoS;
        this.servS = servS;
        this.repoSuc = repoSuc;
        this.servSuc = servSuc;
    }
	
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Ejecutando prueba rápida con datos hardcodeados...");

       
        Disponible disp = new Disponible(LocalTime.of(10,00) , LocalTime.of(18,00),true, true, true,true, true, true, false);
        Servicio srv = new Servicio("Peluqueria", "Corte de pelo", LocalTime.of(0, 30),disp);
        
        Sucursal suc = servSuc.traerPorId(1);
        
        Set<Sucursal> sucursales = new HashSet<>(); //creo el set de sucursales
        sucursales.add(suc); //le meto la q cree recien
        srv.setSucursales(sucursales); //se la seteo a servicio
        
        // Mostrar datos por consola (o usar repositorios si ya los tenés)
        System.out.println("Servicio:" + srv.toString());
        
        servS.agregar(srv);
        
        // Aquí podrías llamar a repositorios para guardar estos datos si los configuraste.
    }*/
	
	//------------------------------------------------
		//| TESTEO DE EMPLEADO Y ASIGNANDOLE UN SERVICIO Y ESAS COSAS |
		//------------------------------------------------	
	private final IEmpleadoRepository repoE;
	private final IEmpleadoService empS;
	private final IServicioRepository repoS;
	private final IServicioService servS;
	
	public TestDataRunner(IEmpleadoRepository repoE, IEmpleadoService empS,IServicioRepository repoS,IServicioService servS) {
        this.repoE = repoE;
        this.empS = empS;
        this.repoS = repoS;
        this.servS = servS;
    }
	
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Ejecutando prueba rápida con datos hardcodeados...");

       Servicio srv = servS.traerPorId(1);
        Direccion dir = new Direccion("Lanu", "Pavon" , 1000);
        Empleado emp = new Empleado("Jorge","unapass","perezito",8888888,"jorgeperezito@gmail.com",dir, "405", srv);
        
        
        // Mostrar datos por consola (o usar repositorios si ya los tenés)
        System.out.println("Empleado:" + emp.toString());
        
        empS.agregar(emp);
        
        // Aquí podrías llamar a repositorios para guardar estos datos si los configuraste.
    }
	
	
}