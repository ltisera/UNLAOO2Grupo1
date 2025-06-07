package com.turnat.TurnAT.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.turnat.TurnAT.models.entities.Turno;
import com.turnat.TurnAT.services.interfaces.ITurnoService;

@Controller
public class TurnoController {
	
	@Autowired
	private ITurnoService turnoService;
	
	@GetMapping("/lista-turnos")
	public String mostrarTurnos ( Model model) {
		 List<Turno> turnos = turnoService.traerTodos();// metodo que devuelve todos los turnos
	     model.addAttribute("turnos", turnos); // le paso la lista al modelo para Thymeleaf
		
		return "turnoAdmin";//retorno la vista de la lista
	}
	
	
}
