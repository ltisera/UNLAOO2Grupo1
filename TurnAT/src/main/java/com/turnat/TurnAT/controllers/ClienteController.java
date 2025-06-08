package com.turnat.TurnAT.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.turnat.TurnAT.models.entities.Cliente;
import com.turnat.TurnAT.models.entities.Turno;
import com.turnat.TurnAT.repositories.IClienteRepository;
import com.turnat.TurnAT.services.interfaces.ITurnoService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
	
	 @Autowired
	    private IClienteRepository clienteRepository;
	
	
	 @GetMapping("/indexCliente")
	 @PostMapping("/indexCliente")   
	 public String clienteInicio() {
	        return "indexCliente";
	    }
	 
	 @GetMapping("/clienteDatos")
	 public String misDatos(Model model, Authentication authentication) {
	        // Obtener el mail del usuario logueado
	        String email = authentication.getName();

	        // Buscar el cliente en la base de datos
	        Cliente cliente = clienteRepository.findByEmail(email)
	                                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

	        // Pasarlo al modelo
	        model.addAttribute("cliente", cliente);

		 System.out.println("mapeo cliente datos");
		 return "clienteDatos";
	 }
	 @Autowired
	 private ITurnoService turnoService;
	 @GetMapping("/clienteTurnos")
	 public String misTurnos(Model model, Authentication authentication) {
		 
		 // Obtener el mail del usuario logueado
	        String email = authentication.getName();

	        // Buscar el cliente en la base de datos
	        Cliente cliente = clienteRepository.findByEmail(email)
	                                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
	        
	        List<Turno> turnos=turnoService.findByIdCliente(cliente.getIdPersona());
	        //if(turnos.isEmpty()) {
	        //	return "vacio";
	       // }
	        	
	        model.addAttribute("turnosCliente", turnos);
	        
	        return "clienteMisTurnos";
	        
	 }
	
	   
	

}
