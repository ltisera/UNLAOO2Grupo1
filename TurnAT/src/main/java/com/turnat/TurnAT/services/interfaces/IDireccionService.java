package com.turnat.TurnAT.services.interfaces;

import java.util.List;

import com.turnat.TurnAT.models.entities.Direccion;

public interface IDireccionService {
	public Direccion agregar(Direccion d);
	public List<Direccion> traerTodos();
	public void eliminar(Direccion d);
	public void eliminar(int idDireccion);
	public Direccion traerPorId(int idDireccion);
	public Direccion actualizar(Direccion direccion);

}
