package com.turnat.TurnAT.services.interfaces;

import java.util.List;

import com.turnat.TurnAT.models.entities.Servicio;


public interface IServicioService {
	public Servicio agregar(Servicio s);
	public List<Servicio> traerTodos();
	public void eliminar(Servicio s);
	public void eliminar(int idServicio);

}
