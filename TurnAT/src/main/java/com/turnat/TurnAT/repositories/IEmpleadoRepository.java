package com.turnat.TurnAT.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turnat.TurnAT.models.entities.Empleado;


public interface IEmpleadoRepository extends JpaRepository <Empleado, Integer> {
	Optional<Empleado> findByEmail(String eMail);

}
