package com.turnat.TurnAT.dto;

import java.time.LocalTime;

public record DisponibleDTO(
    LocalTime horaInicio,
    LocalTime horaFin,
    boolean lunes,
    boolean martes,
    boolean miercoles,
    boolean jueves,
    boolean viernes,
    boolean sabado,
    boolean domingo
) {}
