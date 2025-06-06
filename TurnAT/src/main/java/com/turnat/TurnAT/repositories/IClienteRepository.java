package com.turnat.TurnAT.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.turnat.TurnAT.models.entities.Cliente;

public interface IClienteRepository extends JpaRepository<Cliente, Integer>{
	
}
