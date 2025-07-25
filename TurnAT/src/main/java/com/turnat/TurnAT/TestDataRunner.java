package com.turnat.TurnAT;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.turnat.TurnAT.repositories.IRolRepository;
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


	private final IRolRepository repoRol;
	private final ITurnoService turnoService;
	private final IClienteService clienteService;
	private final IServicioService servicioService;
	private final ISucursalService sucursalService;
	private final IEstadoService estadoService;
	private final IFechaYHoraService fechaYHoraService;
	private final IRolService rolService;
	private final IEmpleadoService empleadoService;
	private final PasswordEncoder passwordEncoder;

	public TestDataRunner(PasswordEncoder passwordEncoder,IRolRepository repoRol, ITurnoService turnoService, IClienteService clienteService, IServicioService servicioService,
						  ISucursalService sucursalService,IEstadoService estadoService, IFechaYHoraService fechaYHoraService,
						  IRolService rolService, IEmpleadoService empleadoService ) {
	    
	    this.repoRol = repoRol;
	    this.turnoService = turnoService;
	    this.clienteService = clienteService;
	    this.servicioService = servicioService;
	    this.estadoService = estadoService;
	    this.fechaYHoraService = fechaYHoraService;
	    this.rolService = rolService;
	    this.empleadoService = empleadoService;
	    this.sucursalService = sucursalService;
	    this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void run(String... args) throws Exception {
	    
		
		
		if(rolService.traerTodos().isEmpty()){
			System.out.println("Ejecutando datos hardcodeados...");
		
		    //TODOS TIENEN Q TENER ESTOS TRES ROLES ASI EN LA BD
		    
		    Rol rolAdmin = new Rol("ADMIN");
		    rolService.agregar(rolAdmin);
		    Rol rolEmp = new Rol("EMPLEADO");
		    rolService.agregar(rolEmp);
		    Rol rolCliente = new Rol("CLIENTE");
		    rolService.agregar(rolCliente); 
		    
		    Estado confirmado = new Estado("confirmado");
		    Estado cancelado = new Estado("cancelado");
		    estadoService.agregar(confirmado);
		    estadoService.agregar(cancelado);
		    
		    //CREAR UN ADMIN
		    Direccion direccionAdmin = new Direccion("Pelermo Hollywood", "Roca", 3000);
		    Disponible disponibleAdmin = new Disponible(LocalTime.of(06, 00), LocalTime.of(22, 00), true, true, true, true, true, false, false);
		    Servicio servicioAdmin = new Servicio("Admin", "Maneja la base de datos", LocalTime.of(11, 30), disponibleAdmin);
		    servicioService.agregar(servicioAdmin);
		    Empleado admin = new Empleado("Patricio", passwordEncoder.encode("admin"), "Rockefeller", 28765432, "admin@example.com", direccionAdmin, "1000",servicioAdmin);
		    empleadoService.agregar(admin);
		    Rol rolA = repoRol.findByNombre("ADMIN").orElseThrow(() -> new RuntimeException("Rol no encontrado")); 
			   
			Set<Rol> rolesA = new HashSet<>(); //se crea el set de rol
			rolesA.add(rolA); //le meto el rol creado o levantado
			admin.setRoles(rolesA);//se lo meto al empleado
			  
			  empleadoService.actualizar(admin);
		
	
		   //CREAR UN EMPLEADO CON ROL EMPLEADO, UN SERVICIO Y UNA SUCURSAL
		    
		    Disponible disponible = new Disponible(LocalTime.of(10, 00), LocalTime.of(18, 00), true, true, true, true, true, false, false);
		    Servicio servicio = new Servicio("Corte de pelo", "corte basico", LocalTime.of(0, 30), disponible);
		    
		    Set<Sucursal> sucursal = new HashSet<>(); //se crea el set de rol
		    Direccion direccionSuc = new Direccion("Caba ", "San Martin", 1810);
		    Sucursal suc = new Sucursal("La barberia","+54 1122334455", direccionSuc);
		    sucursalService.agregar(suc); 
		    sucursal.add(suc);
		    servicio.setSucursales(sucursal);
		    servicioService.agregar(servicio);
		    Direccion direccionEmpleado = new Direccion("Lanus ", "Pavon", 4333);
		    Empleado empleado = new Empleado("Marcelo", passwordEncoder.encode("password"), "Perez", 22222222, "marcelo@example.com", direccionEmpleado, "1001", servicio);
		    empleadoService.agregar(empleado);
		    Rol rol = repoRol.findByNombre("EMPLEADO").orElseThrow(() -> new RuntimeException("Rol no encontrado")); 
			   
			Set<Rol> roles = new HashSet<>(); //se crea el set de rol
			roles.add(rol); //le meto el rol creado o levantado
			empleado.setRoles(roles);//se lo meto al empleado
			  
			  empleadoService.actualizar(empleado);
		    
		    
		 // Crear un cliente
		    Direccion direccionCliente = new Direccion("Lomas de Zamora ", "Gorriti", 1234);
		    Cliente cliente = new Cliente("Martin", passwordEncoder.encode("password"), "Marinez", 34220456, "martin@example.com", direccionCliente, "+54 11 2222 2222");
		    clienteService.agregar(cliente);
		    Rol rolCli = repoRol.findByNombre("CLIENTE").orElseThrow(() -> new RuntimeException("Rol no encontrado")); 
			   
		  		Set<Rol> rolC = new HashSet<>(); //se crea el set de rol
		  		rolC.add(rolCli); //le meto el rol creado o levantado
		  		cliente.setRoles(rolC);//se lo meto al empleado
		  		clienteService.actualizar(cliente);
		  		  
		    // Crear un servicio
		    Disponible disponible2 = new Disponible(LocalTime.of(9, 00), LocalTime.of(17, 00), true, true, true, true, true, true, false);
		    Servicio servicio2 = new Servicio("Tintura de pelo", "Te podes teñir toda la cabeza", LocalTime.of(0, 45), disponible2);
		    servicio2.setSucursales(sucursal);
		    servicioService.agregar(servicio2);
		
		    // Crear un estado
		    Estado estado = estadoService.traerPorId(1);//confirmado
			
		    // Crear una fecha y hora
		    FechaYHora fechaYHora = new FechaYHora(LocalDate.of(2025,8,15), LocalTime.of(15, 30));
		    fechaYHoraService.agregar(fechaYHora);
		    
		    
		    
		    // Crear un turno
		    Turno turno = new Turno(cliente, servicio, estado, fechaYHora);
		    turnoService.agregar(turno);
		    System.out.println("===========CARGA INICIAL DE DATOS PARA EL SISTEMA COMPLETADA===========");
		    System.out.println("ADMIN: Patricio Rockefeller, mail:admin@example.com, contraseña: admin ");
		    System.out.println("Cliente: Martin Martinez, mail:martin@example.com, contraseña: password ");
		    
		}		
	    
	}

		
	
}