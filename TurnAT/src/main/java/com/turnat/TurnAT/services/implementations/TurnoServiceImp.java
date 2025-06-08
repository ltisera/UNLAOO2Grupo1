package com.turnat.TurnAT.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.turnat.TurnAT.models.entities.Turno;
import com.turnat.TurnAT.repositories.ITurnoRepository;
import com.turnat.TurnAT.services.interfaces.ITurnoService;

@Service
public class TurnoServiceImp implements ITurnoService {

    private final ITurnoRepository turnoRepo;

    public TurnoServiceImp(ITurnoRepository turnoRepo) {
        this.turnoRepo = turnoRepo;
    }

    @Override
    public Turno agregar(Turno turno) {
        return turnoRepo.save(turno);
    }

    @Override
    public List<Turno> traerTodos() {
        return turnoRepo.findAll();
    }

    @Override
    public void eliminar(int idTurno) {
        turnoRepo.deleteById(idTurno);
    }

    @Override
    public Turno traerPorId(int idTurno) {
        return turnoRepo.findById(idTurno).orElseThrow(() -> new RuntimeException("Turno no encontrado"));
    }
    
   public List<Turno> findByIdCliente(int idCliente){
    	
    	return turnoRepo.findByIdCliente(idCliente);
    }

	
    
    
}
