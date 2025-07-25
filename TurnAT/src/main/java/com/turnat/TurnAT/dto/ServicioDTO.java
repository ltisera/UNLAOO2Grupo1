package com.turnat.TurnAT.dto;

import java.util.List;

public record ServicioDTO(
    int idServicio,
    String nombre,
    String descripcion,
    Integer duracionMinutos,
    List<DisponibleDTO> disponibles
) {}
