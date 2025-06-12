package com.turnat.TurnAT.services.interfaces;

import com.turnat.TurnAT.models.entities.Disponible;
import java.util.List;

public interface IDisponibleService {
    List<Disponible> traerTodos();
    Disponible traerPorId(int id);
    void agregar(Disponible disponible);
    void eliminar(int id);
}
