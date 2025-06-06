package com.turnat.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class loginController {
	
	@GetMapping("/")
	
	public String login() {
		System.out.println("golpeo en login algo ya esta dale play avisame ");
		return "login";
    }
	
	
}