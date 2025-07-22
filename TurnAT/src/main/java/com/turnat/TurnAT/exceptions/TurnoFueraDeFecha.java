package com.turnat.TurnAT.exceptions;

public class TurnoFueraDeFecha extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public TurnoFueraDeFecha(String mensaje) {
		super(mensaje);
	}
}
