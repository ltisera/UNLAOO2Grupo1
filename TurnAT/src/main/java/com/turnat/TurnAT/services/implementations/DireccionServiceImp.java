package com.turnat.TurnAT.services.implementations;

import java.util.List;

import com.turnat.TurnAT.models.entities.Direccion;
import com.turnat.TurnAT.repositories.IDireccionRepository;
import com.turnat.TurnAT.services.interfaces.IDireccionService;

public class DireccionServiceImp implements IDireccionService  {

	private final IDireccionRepository repoDireccion;
	
	public DireccionServiceImp(IDireccionRepository repoDireccion) {
		this.repoDireccion = repoDireccion;
	}
	
	@Override
	public Direccion agregar(Direccion d) {
		return repoDireccion.save(d);
	}

	@Override
	public List<Direccion> traerTodos() {
		return repoDireccion.findAll();
	}

	@Override
	public void eliminar(Direccion d) {
		repoDireccion.deleteById(d.getIdDireccion());
		
	}

	@Override
	public void eliminar(int idDireccion) {
		repoDireccion.deleteById(idDireccion);
		
	}

	@Override
	public Direccion traerPorId(int idDireccion) {
		return repoDireccion.findById(idDireccion).orElseThrow(()-> new RuntimeException("Direccion no encontrada"));
	}
	
	

}
