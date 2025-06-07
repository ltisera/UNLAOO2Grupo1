package com.turnat.TurnAT.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.turnat.TurnAT.models.entities.Rol;
import com.turnat.TurnAT.repositories.IRolRepository;
import com.turnat.TurnAT.services.interfaces.IRolService;

@Service
public class RolServiceImp implements IRolService{

	private final IRolRepository repoRol;
	
	public RolServiceImp( IRolRepository repoRol) {
		this.repoRol = repoRol;
	}
	
	@Override
	public Rol agregar(Rol rol) {
		return repoRol.save(rol);
	}

	@Override
	public List<Rol> traerTodos() {
		return repoRol.findAll();
	
	}

	@Override
	public void eliminar(int idRol) {
		repoRol.deleteById(idRol);
	}

	@Override
	public Rol traerPorId(int idRol) {
		return repoRol.findById(idRol).orElseThrow(() -> new RuntimeException("Rol no encontrado"));	
	}
	

}
