package com.turnat.TurnAT.dto;

public record SolicitudTurnoDTO (
	int idServicio,
    int anio,
    int mes,
    int dia,
    String hora,
    int idCliente
) {}
   