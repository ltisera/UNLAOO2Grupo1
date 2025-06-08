package com.turnat.TurnAT.services.implementations;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.turnat.TurnAT.models.entities.Persona;
import com.turnat.TurnAT.repositories.IClienteRepository;
import com.turnat.TurnAT.repositories.IEmpleadoRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IClienteRepository clienteRepository;

    @Autowired
    private IEmpleadoRepository empleadoRepository;

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {

        Optional<? extends Persona> personaOpt = clienteRepository.findByEmail(mail)
            .map(cliente -> (Persona) cliente);

        if (personaOpt.isEmpty()) {
            personaOpt = empleadoRepository.findByEmail(mail)
                .map(empleado -> (Persona) empleado);
        }

        Persona persona = personaOpt.orElseThrow(() ->
            new UsernameNotFoundException("Usuario no encontrado: " + mail));

        return new User(
            persona.getEmail(),
            persona.getPassword(),
            persona.getRoles().stream()
                .map(rol -> new SimpleGrantedAuthority("ROLE_" + rol.getNombre()))
                .toList()
        );
    }
}
