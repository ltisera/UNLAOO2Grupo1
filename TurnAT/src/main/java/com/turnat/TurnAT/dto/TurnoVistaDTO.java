package com.turnat.TurnAT.dto;

public record TurnoVistaDTO(
	int idTurno,
	String fecha,
    String hora,
    String sucursal,
    String servicio,
    String estado
) {}
