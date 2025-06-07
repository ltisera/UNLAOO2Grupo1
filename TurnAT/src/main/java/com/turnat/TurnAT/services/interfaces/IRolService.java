package com.turnat.TurnAT.services.interfaces;

import java.util.List;

import com.turnat.TurnAT.models.entities.Rol;


public interface IRolService {

	    Rol agregar(Rol rol);
	    List<Rol> traerTodos();
	    void eliminar(int idRol);
	    Rol traerPorId(int idRol);
	
}
