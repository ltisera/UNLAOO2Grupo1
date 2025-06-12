package com.turnat.TurnAT;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.turnat.TurnAT.models.entities.Empleado;
import com.turnat.TurnAT.models.entities.Cliente;
import com.turnat.TurnAT.models.entities.Direccion;
import com.turnat.TurnAT.models.entities.Disponible;
import com.turnat.TurnAT.models.entities.Rol;
import com.turnat.TurnAT.models.entities.Servicio;
import com.turnat.TurnAT.repositories.IClienteRepository;
import com.turnat.TurnAT.repositories.IRolRepository;
import com.turnat.TurnAT.services.interfaces.IClienteService;
import com.turnat.TurnAT.services.interfaces.IEmailService;
import com.turnat.TurnAT.services.interfaces.IEmpleadoService;
import com.turnat.TurnAT.services.interfaces.IRolService;
import com.turnat.TurnAT.services.interfaces.IServicioService;

@Component
public class EmailSenderRunner implements CommandLineRunner {

    private final IClienteService ClienteService;
    private final PasswordEncoder passwordEncoder;
    private final IEmailService emailService;
    private final IRolRepository repoRol;
    private final IRolService rolService;
    private final IEmpleadoService EmpleadoService;
    private final IServicioService servicioService;
    public EmailSenderRunner( IServicioService servicioService, IEmpleadoService EmpleadoService, IClienteService clienteService, PasswordEncoder passwordEncoder, IEmailService emailService,IRolRepository repoRol,IRolService rolService) {
        this.repoRol = repoRol;
    	this.ClienteService = clienteService;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.rolService = rolService;
        this.EmpleadoService = EmpleadoService;
        this.servicioService = servicioService;
    }

    @Override
    public void run(String... args) {
    	/*
    	//TODOS TIENEN Q TENER ESTOS TRES ROLES ASI EN LA BD
 	    Rol rolAdmin = new Rol("ADMIN");
 	    rolService.agregar(rolAdmin);
 	   Rol rolEmp = new Rol("EMPLEADO");
 	   rolService.agregar(rolEmp);
 	    Rol rolCliente = new Rol("CLIENTE");
 	   rolService.agregar(rolCliente); 
 	   */
    	
        // Crear la dirección
        //Direccion direccion = new Direccion("CABA", "Calle Inventada", 456);

        //Crear el cliente con el constructor completo
       /* Cliente nuevoCliente = new Cliente(
                "Santiago",                        // nombre
                passwordEncoder.encode("papa"), // password encriptada
                "Serrano",                         // apellido
                455646518,                          // DNI
                "santiagogonzaloserrano@gmail.com",// email
                 null ,                       // dirección
                "1122334455"                       // teléfono
        );
        // Guardar el cliente
		  ClienteService.agregar(nuevoCliente);
        Rol rol = repoRol.findByNombre("CLIENTE").orElseThrow(() -> new RuntimeException("Rol no encontrado")); 
		   
		Set<Rol> roles = new HashSet<>(); //se crea el set de rol
		roles.add(rol); //le meto el rol creado o levantado
		nuevoCliente.setRoles(roles);//se lo meto al cliente
		
		  
		  ClienteService.actualizar(nuevoCliente);

       */
        //Crear el admin con el constructor completo
    	/* Disponible disp = new Disponible(LocalTime.of(10,00) , LocalTime.of(18,00),true, true, true,true, true, true, true);
         Servicio srv = new Servicio("Administracion", "Maneja la base de datos", LocalTime.of(8,0),disp);
         servicioService.agregar(srv);
        Empleado nuevoEmpleado = new Empleado(
                "Santiago",                        // nombre
                passwordEncoder.encode("papa"), // password encriptada
                "Serrano",                         // apellido
                455646518,                          // DNI
                "santiagogonzaloserrano@gmail.com",// email
                 null ,                       // dirección
                "1122334455",				//legajo
                srv							//servicio
               
        );
        // Guardar el cliente
		  EmpleadoService.agregar(nuevoEmpleado);
        Rol rol = repoRol.findByNombre("ADMIN").orElseThrow(() -> new RuntimeException("Rol no encontrado")); 
		   
		Set<Rol> roles = new HashSet<>(); //se crea el set de rol
		roles.add(rol); //le meto el rol creado o levantado
		nuevoEmpleado.setRoles(roles);//se lo meto al empleado
		
		  
		 EmpleadoService.actualizar(nuevoEmpleado);
*/

        // Enviar correo de bienvenida
        //String asunto = "¡Bienvenido a TurnAT!";
        //String cuerpo = "Hola Santiago, este es un correo de prueba enviado desde el sistema de turnos. ¡Todo está funcionando correctamente!";
       // emailService.enviarCorreo("santiagogonzaloserrano@gmail.com", asunto, cuerpo);

    }
}
