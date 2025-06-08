package com.turnat.TurnAT.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turnat.TurnAT.models.entities.Rol;


public interface IRolRepository extends JpaRepository <Rol, Integer> {
	 Optional<Rol> findByNombre(String nombre);

}
