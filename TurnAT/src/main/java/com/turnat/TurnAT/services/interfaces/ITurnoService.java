package com.turnat.TurnAT.services.interfaces;

import java.util.List;

import com.turnat.TurnAT.models.entities.Turno;

public interface ITurnoService {
    Turno agregar(Turno turno);
    List<Turno> traerTodos();
    void eliminar(int idTurno);
    Turno traerPorId(int idTurno);
    List<Turno> findByIdCliente(int idCliente);
    
}
