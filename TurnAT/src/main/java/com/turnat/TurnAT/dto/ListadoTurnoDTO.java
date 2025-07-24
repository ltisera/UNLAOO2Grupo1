package com.turnat.TurnAT.dto;

public record ListadoTurnoDTO(
		int idTurno,
		String clienteNombre,
		String servicio,
		String sucursalNombre,
		String estado,
		int dia,
	    int mes,
	    int anio,
	    String hora
		
		
){}
