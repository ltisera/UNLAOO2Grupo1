package com.turant.TurnAT.exceptions;

public class ServicioHoraInicioYFin extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ServicioHoraInicioYFin(String mensaje) {
		super(mensaje);
	}
}
