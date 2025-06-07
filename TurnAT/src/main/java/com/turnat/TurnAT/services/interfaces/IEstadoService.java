package com.turnat.TurnAT.services.interfaces;

import java.util.List;

import com.turnat.TurnAT.models.entities.Estado;
import com.turnat.TurnAT.models.entities.Sucursal;

public interface IEstadoService {

	public Estado agregar(Estado e);
	public List<Estado> traerTodos();
	public void eliminar(Estado e);
	public void eliminar(int idEstado);
	public Estado traerPorId(int idEstado);
}
