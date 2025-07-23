package com.turnat.TurnAT.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.turnat.TurnAT.exceptions.*;

@ControllerAdvice
public class ExceptionsController {
	 @ExceptionHandler(ServicioHoraInicioYFin.class )
	 public String manejarServicioFueraDeHora(ServicioHoraInicioYFin exception, Model model) {
        model.addAttribute("error", exception.getMessage());
        return "errores/horaInicioYFin"; // vista HTML del error
    }
	 	
 	@ExceptionHandler(TurnoFueraDeFecha.class)
 	public String manejarTurnoFueraDeFecha(TurnoFueraDeFecha exception, Model model){
 		System.out.println("Estoy en esoooooooooooo");
 		model.addAttribute("error", exception.getMessage());
 		return "errores/TurnoFueraDeFecha";
 	}

 	@ExceptionHandler(DniDuplicadoException.class)
 	public String manejarDniDuplicado(DniDuplicadoException exception, Model model) {
 	    model.addAttribute("error", exception.getMessage());
 	    return "errores/dniDuplicado"; // Vista HTML del error
 	}

}
