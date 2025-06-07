package com.turnat.TurnAT.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turnat.TurnAT.models.entities.Estado;
import com.turnat.TurnAT.models.entities.Rol;
import com.turnat.TurnAT.repositories.IEstadoRepository;
import com.turnat.TurnAT.services.interfaces.IEstadoService;

@Service
public class EstadoServiceImp implements IEstadoService{

    @Autowired
    private IEstadoRepository estadoRepository;
    public EstadoServiceImp (IEstadoRepository estadoRepository) {
    	this.estadoRepository = estadoRepository;
   
    }
    

    public Estado agregar(String descripcion) {
        Estado estado = new Estado(descripcion);
        return estadoRepository.save(estado);
    }
    
    @Override
	public List<Estado> traerTodos() {
		return estadoRepository.findAll();
	
	}

	@Override
	public void eliminar(int idEstado) {
		estadoRepository.deleteById(idEstado);
	}
	
	public void eliminar(Estado e) {
		estadoRepository.deleteById(e.getIdEstado());
	}

	@Override
	public Estado traerPorId(int idEstado) {
		return estadoRepository.findById(idEstado).orElseThrow(() -> new RuntimeException("Estado no encontrado"));	
	}


	@Override
	public Estado agregar(Estado e) {
		return estadoRepository.save(e);
	}
	
    
    
}
