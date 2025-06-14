package com.turnat.TurnAT.repositories;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.turnat.TurnAT.models.entities.FechaYHora;

public interface IFechaYHoraRepository extends JpaRepository<FechaYHora, Integer>{
	Optional<FechaYHora> findByFechaAndHora(LocalDate fecha, LocalTime hora);
}
