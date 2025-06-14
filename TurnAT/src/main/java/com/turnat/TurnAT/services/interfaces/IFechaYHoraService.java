package com.turnat.TurnAT.services.interfaces;

import java.time.LocalDate;
import java.time.LocalTime;

import com.turnat.TurnAT.models.entities.FechaYHora;

public interface IFechaYHoraService {
    FechaYHora agregar(FechaYHora f);
    void eliminar(int id);
    FechaYHora buscarPorFechaHora(LocalDate fecha, LocalTime hora);
}
