package com.turnat.TurnAT.services.interfaces;

import java.util.List;

import com.turnat.TurnAT.models.entities.Empleado;


public interface IEmpleadoService {

	public Empleado agregar(Empleado e);
	public List<Empleado> traerTodos();
	public void eliminar(Empleado e);
	public void eliminar(int idEmpleado);
	public Empleado traerPorId(int idEmpleado);
	
}
