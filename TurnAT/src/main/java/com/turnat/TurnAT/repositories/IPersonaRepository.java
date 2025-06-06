package com.turnat.TurnAT.repositories;

import com.turnat.TurnAT.models.entities.Persona;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPersonaRepository extends JpaRepository<Persona, Integer> {

    Optional<Persona> findById(Integer integer);

    Optional<Persona> findByEmail(String email);

    @Query(value = "from persona p order by p.idPersona")
    List<Persona> findAll();
   
    
}