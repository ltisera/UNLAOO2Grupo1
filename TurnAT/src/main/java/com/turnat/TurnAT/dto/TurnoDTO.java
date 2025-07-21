package com.turnat.TurnAT.dto;


public record TurnoDTO(
    int idServicio,
    int idSucursal,
    int anio,
    int mes,
    int dia,
    String hora,
    int idCliente
) {}
