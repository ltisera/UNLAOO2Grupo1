package com.turnat.TurnAT.services.implementations;

import com.turnat.TurnAT.models.entities.Disponible;
import com.turnat.TurnAT.repositories.DisponibleRepository; // Asegúrate de tener este repositorio
import com.turnat.TurnAT.services.interfaces.IDisponibleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisponibleServiceImp implements IDisponibleService {

    @Autowired
    private DisponibleRepository disponibleRepository;

    @Override
    public List<Disponible> traerTodos() {
        return disponibleRepository.findAll();
    }

    @Override
    public Disponible traerPorId(int id) {
        return disponibleRepository.findById(id).orElse(null);
    }

    @Override
    public void agregar(Disponible disponible) {
        disponibleRepository.save(disponible);
    }

    @Override
    public void eliminar(int id) {
        disponibleRepository.deleteById(id);
    }
}
