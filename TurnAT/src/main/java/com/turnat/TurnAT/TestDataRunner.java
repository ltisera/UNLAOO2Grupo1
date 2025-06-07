package com.turnat.TurnAT;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;

import org.springframework.stereotype.Component;
import com.turnat.TurnAT.models.entities.Estado;
import com.turnat.TurnAT.models.entities.FechaYHora;
import com.turnat.TurnAT.models.entities.Rol;
import com.turnat.TurnAT.models.entities.Servicio;
import com.turnat.TurnAT.models.entities.Sucursal;
import com.turnat.TurnAT.models.entities.Turno;
import com.turnat.TurnAT.models.entities.Direccion;
import com.turnat.TurnAT.models.entities.Disponible;
import com.turnat.TurnAT.models.entities.Empleado;
import com.turnat.TurnAT.models.entities.Cliente;
import com.turnat.TurnAT.repositories.IClienteRepository;
import com.turnat.TurnAT.repositories.IEmpleadoRepository;
import com.turnat.TurnAT.repositories.IEstadoRepository;
import com.turnat.TurnAT.repositories.IFechaYHoraRepository;
import com.turnat.TurnAT.repositories.IServicioRepository;
import com.turnat.TurnAT.repositories.ISucursalRepository;
import com.turnat.TurnAT.repositories.ITurnoRepository;
import com.turnat.TurnAT.services.interfaces.IClienteService;
import com.turnat.TurnAT.services.interfaces.IEmpleadoService;
import com.turnat.TurnAT.services.interfaces.IEstadoService;
import com.turnat.TurnAT.services.interfaces.IFechaYHoraService;
import com.turnat.TurnAT.services.interfaces.IRolService;
import com.turnat.TurnAT.services.interfaces.IServicioService;
import com.turnat.TurnAT.services.interfaces.ISucursalService;
import com.turnat.TurnAT.services.interfaces.ITurnoService;

@Component

public class TestDataRunner implements CommandLineRunner {
	
	
	/*// testeo de sucursal
	
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
	*/
	
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
    }
	*/
	//------------------------------------------------
		//| TESTEO DE EMPLEADO Y ASIGNANDOLE UN SERVICIO Y ESAS COSAS |
	/*	//------------------------------------------------	
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
	*/
	
	//------------------------------------------------------
	//  Test de Turno waos:
	//------------------------------------------------------
	//------------------------------------------------------

	private final ITurnoRepository repoTurno;
	private final ITurnoService turnoService;
	private final IClienteService clienteService;
	private final IServicioService servicioService;
	private final IEstadoService estadoService;
	private final IFechaYHoraService fechaYHoraService;
	private final IRolService rolService;
	private final IEmpleadoService empleadoService;

	public TestDataRunner(ITurnoRepository repoTurno, ITurnoService turnoService, 
	                      IClienteService clienteService, IServicioService servicioService,
	                      IEstadoService estadoService, IFechaYHoraService fechaYHoraService,IRolService rolService, IEmpleadoService empleadoService ) {
	    this.repoTurno = repoTurno;
	    this.turnoService = turnoService;
	    this.clienteService = clienteService;
	    this.servicioService = servicioService;
	    this.estadoService = estadoService;
	    this.fechaYHoraService = fechaYHoraService;
	    this.rolService = rolService;
	    this.empleadoService = empleadoService;
	}

	@Override
	public void run(String... args) throws Exception {
	    System.out.println("Ejecutando prueba rápida con datos hardcodeados...");
	
	    
	  /*  // Crear un cliente
	    Direccion direccionCliente = new Direccion("Lome ", "calle", 34);
	    Cliente cliente = new Cliente("pablo", "password", "lopez", 87654321, "pablo@example.com", direccionCliente, "+54 11 2222 2222");
	    clienteService.agregar(cliente);
	
	    // Crear un servicio
	    Disponible disponible = new Disponible(LocalTime.of(00, 00), LocalTime.of(8, 00), true, true, true, true, true, true, false);
	    Servicio servicio = new Servicio("Corte", "corte nocturno ", LocalTime.of(0, 30), disponible);
	    servicioService.agregar(servicio);
	
	    // Crear un estado
	    Estado estado = estadoService.traerPorId(1);
		
	    // Crear una fecha y hora
	    FechaYHora fechaYHora = new FechaYHora(LocalDate.now(), LocalTime.of(15, 00));
	    fechaYHoraService.agregar(fechaYHora);
	    fechaYHoraService.eliminar(23);
	    
	    
	    // Crear un turno
	    Turno turno = new Turno(cliente, servicio, estado, fechaYHora);
	    Turno turnoGuardado = turnoService.agregar(turno);
		
	    // Mostrar datos por consola
	    */
	    
	   /* Rol rol = new Rol("Empleado");
	    rolService.agregar(rol);
	    //Rol rol = rolService.traerPorId(1); //rol cliente
	   Empleado emp = empleadoService.traerPorId(3);
	    
	    Set<Rol> roles = new HashSet<>(); //se crea el set de rol
	    roles.add(rol); //le meto el rol creado o levantado
	   emp.setRoles(roles);//se lo meto al cliente
	  
	   empleadoService.actualizar(emp);
	    System.out.println(rol.toString());
	    System.out.println(emp.getRoles());
	   */
	}

		
	
}