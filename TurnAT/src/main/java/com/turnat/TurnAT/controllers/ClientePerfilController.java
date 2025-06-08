package com.turnat.TurnAT.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.turnat.TurnAT.models.entities.Cliente;
import com.turnat.TurnAT.models.entities.Turno;
import com.turnat.TurnAT.services.interfaces.IClienteService;

@Controller
public class ClientePerfilController {
	
	/*@Autowired
	private IClienteService clienteService;
	
	@GetMapping("/ver-perfil-cliente")
	public String clienteDatos ( Model model) {
		 List<Cliente> cliente = clienteService.traerTodos();// metodo que devuelve todos los turnos
	     model.addAttribute("cliente", cliente); // le paso la lista al modelo para Thymeleaf
		
		return "turnoAdmin";//retorno la vista de la lista
	}*/
	

}
