package com.turnat.TurnAT.services.interfaces;

import java.util.List;

import com.turnat.TurnAT.dto.SolicitudTurnoDTO;
import com.turnat.TurnAT.models.entities.Turno;

public interface ITurnoService {
    Turno agregar(Turno turno);
    List<Turno> traerTodos();
    void eliminar(int idTurno);
    void eliminarTodos(List<Turno> turnos);
    Turno traerPorId(int idTurno);
    Turno actualizar(Turno turno);
    List<Turno> traerPorIdCliente(int idCliente);
    
    
    List<Integer> obtenerDiasDisponibles(int idServicio, int idSucursal, int anio, int mes);//String para los horarios  para el frontend en JSON es más práctico como String.
    List<String> obtenerHorariosDisponibles(int idServicio, int idSucursal, int anio, int mes, int dia);
	void confirmarTurno(SolicitudTurnoDTO dto);
	
}
