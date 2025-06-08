package com.turnat.TurnAT.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
	@RequestMapping("/empleado")
public class EmpleadoController {
	
	
	    @GetMapping("/blancoEmp")
	    public String EmpleadoInicio() {
	        return "blancoEmp";
	    }
	
}
