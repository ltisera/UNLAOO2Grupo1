package com.turnat.TurnAT.services.implementations;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turnat.TurnAT.models.entities.FechaYHora;
import com.turnat.TurnAT.repositories.IFechaYHoraRepository;
import com.turnat.TurnAT.services.interfaces.IFechaYHoraService;

@Service
public class FechaYHoraServiceImp implements IFechaYHoraService {

    @Autowired
    private IFechaYHoraRepository fechaYHoraRepo;

    public void eliminar(int id) {
    	fechaYHoraRepo.deleteById(id);
    }
    @Override
    public FechaYHora agregar(FechaYHora f) {
        return fechaYHoraRepo.save(f);
    }
    @Override
    public FechaYHora buscarPorFechaHora(LocalDate fecha, LocalTime hora) {
        return fechaYHoraRepo.findByFechaAndHora(fecha, hora).orElse(null);
    }
}
