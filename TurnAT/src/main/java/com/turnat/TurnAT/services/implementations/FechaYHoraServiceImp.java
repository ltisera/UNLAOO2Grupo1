package com.turnat.TurnAT.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turnat.TurnAT.models.entities.FechaYHora;
import com.turnat.TurnAT.repositories.IFechaYHoraRepository;
import com.turnat.TurnAT.services.interfaces.IFechaYHoraService;

@Service
public class FechaYHoraServiceImp implements IFechaYHoraService {

    @Autowired
    private IFechaYHoraRepository fechaYHoraRepo;

    @Override
    public FechaYHora agregar(FechaYHora f) {
        return fechaYHoraRepo.save(f);
    }
}
