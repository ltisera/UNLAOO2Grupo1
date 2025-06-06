package com.turnat.TurnAT.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turnat.TurnAT.models.entities.Estado;

public interface IEstadoRepository extends JpaRepository<Estado, Integer> {
	
}
