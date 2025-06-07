package com.turnat.TurnAT.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turnat.TurnAT.models.entities.Empleado;


public interface IEmpleadoRepository extends JpaRepository <Empleado, Integer> {

}
