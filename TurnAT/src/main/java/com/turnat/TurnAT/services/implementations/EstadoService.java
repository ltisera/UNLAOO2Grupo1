package com.turnat.TurnAT.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turnat.TurnAT.models.entities.Estado;
import com.turnat.TurnAT.repositories.IEstadoRepository;

@Service
public class EstadoService {

    @Autowired
    private IEstadoRepository estadoRepository;

    public Estado agregarEstado(String descripcion) {
        Estado estado = new Estado(descripcion);
        return estadoRepository.save(estado);
    }
}
