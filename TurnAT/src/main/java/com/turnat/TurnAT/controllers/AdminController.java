package com.turnat.TurnAT.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.turnat.TurnAT.models.entities.Cliente;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@GetMapping("/indexAdmin")
    public String adminInicio() {
		System.out.println("mapeo ADMIN");
        return "indexAdmin";
    }
	@GetMapping("/sucursalAdmin")
	
	 public String misDatos(Authentication authentication) {
	        
		 System.out.println("mapeo ADMIN SUCURSAL");
		 return "sucursalAdmin";
	 }
    
    
}
