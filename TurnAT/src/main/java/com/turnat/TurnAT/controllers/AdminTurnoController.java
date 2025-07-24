package com.turnat.TurnAT.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.turnat.TurnAT.models.entities.Turno;
import com.turnat.TurnAT.services.interfaces.ITurnoService;

@Controller
@RequestMapping("/admin/turno")
public class AdminTurnoController {
	
	@Autowired
	private ITurnoService turnoService;
	
	@GetMapping("/listado")
	public String mostrarTurnos ( Model model) {
		 List<Turno> turnos = turnoService.traerTodos();// metodo que devuelve todos los turnos
	     model.addAttribute("turnos", turnos); // le paso la lista al modelo para Thymeleaf
		
		return "turnoAdminListado";//retorno la vista 
	}
	
	
	
	
}
