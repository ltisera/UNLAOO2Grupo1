package com.turnat.TurnAT.services.implementations;

import com.turnat.TurnAT.models.entities.Persona;
import com.turnat.TurnAT.repositories.IPersonaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;

@Service
public class PersonaServiceImp {

	@Autowired
    private IPersonaRepository personaRepo;
	
	public Persona guardarPersona(Persona p) {
        // Puede ser Cliente o Empleado o Persona directa (aunque es abstracta, no se instancia)
        return personaRepo.save(p);
    }

}