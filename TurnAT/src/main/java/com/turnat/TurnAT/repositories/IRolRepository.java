package com.turnat.TurnAT.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turnat.TurnAT.models.entities.Rol;


public interface IRolRepository extends JpaRepository <Rol, Integer> {
	

}
