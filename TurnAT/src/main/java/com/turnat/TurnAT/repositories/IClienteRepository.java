package com.turnat.TurnAT.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.turnat.TurnAT.models.entities.Cliente;

public interface IClienteRepository extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findByEmail(String eMail);
    Optional<Cliente> findByDni(int dni); // Método para buscar por DNI
}
