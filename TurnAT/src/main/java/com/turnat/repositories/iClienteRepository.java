package com.turnat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turnat.entities.Cliente;

public interface iClienteRepository extends JpaRepository<Cliente, Integer> {
	// Podés agregar consultas personalizadas si querés
    Cliente findByDni(int dni);
}
