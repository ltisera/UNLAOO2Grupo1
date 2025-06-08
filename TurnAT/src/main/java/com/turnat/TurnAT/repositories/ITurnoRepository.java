package com.turnat.TurnAT.repositories;


import com.turnat.TurnAT.models.entities.Turno;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ITurnoRepository extends JpaRepository<Turno, Integer> {
	
	@Query("SELECT t FROM Turno t WHERE t.cliente.idPersona = :idCliente")
	List<Turno> findByIdCliente(@Param("idCliente") int idCliente);
	
}
