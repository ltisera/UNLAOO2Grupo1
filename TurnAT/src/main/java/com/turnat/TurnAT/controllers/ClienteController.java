package com.turnat.TurnAT.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
	
	 @GetMapping("/indexCliente")
	 @PostMapping("/indexCliente")   
	 public String clienteInicio() {
	        return "indexCliente";
	    }
	 
	 @GetMapping("/clienteDatos")
	 public String misDatos(){
		 System.out.println("mapeo cliente datos");
		 return "clienteDatos";
	 }
	
	   
	

}
