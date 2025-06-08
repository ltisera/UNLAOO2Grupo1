package com.turnat.TurnAT.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
	
	 @GetMapping("/blancoCliente")
	    public String clienteInicio() {
	        return "blancoCliente";
	    }
	
	   
	

}
