package com.turnat.TurnAT.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.turant.TurnAT.exceptions.*;

@ControllerAdvice
public class ExceptionsController {
	 @ExceptionHandler(ServicioHoraInicioYFin.class )
	    public String manejarTurnoFueraDeFecha(ServicioHoraInicioYFin exception, Model model) {
	        model.addAttribute("error", exception.getMessage());
	        return "errores/horaInicioYFin"; // vista HTML del error
	    }


}
