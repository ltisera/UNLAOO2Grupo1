package com.turnat.TurnAT.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.turnat.TurnAT.exceptions.ServicioHoraInicioYFin;
import com.turnat.TurnAT.models.entities.Servicio;
import com.turnat.TurnAT.repositories.IServicioRepository;
import com.turnat.TurnAT.services.interfaces.IServicioService;

@Service
public class ServicioServiceImp implements IServicioService{
	private final IServicioRepository servicioRepo;
	
	public ServicioServiceImp(IServicioRepository servicioRepo) {
		this.servicioRepo = servicioRepo;
	}

	@Override
	public Servicio agregar(Servicio s) {
		if(s.getDisponibilidad().getHoraInicio().isAfter(s.getDisponibilidad().getHoraFin())) {
			throw new ServicioHoraInicioYFin("La hora de inicio selecionada es despues de a hora de finalizacion seleccionada");
		}
		return servicioRepo.save(s);
	}

	@Override
	public List<Servicio> traerTodos() {
		
		return servicioRepo.findAll();
	}

	@Override
	public void eliminar(Servicio s) {
		servicioRepo.deleteById(s.getIdServicio());
		
	}

	@Override
	public void eliminar(int idServicio) {
		servicioRepo.deleteById(idServicio);
		
	}
	
	public Servicio traerPorId(int idServicio) {
		return servicioRepo.findById(idServicio).orElseThrow(() -> new RuntimeException("Servicio no encontrado"));
	}
	
	

}
