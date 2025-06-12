/*
package com.turnat.TurnAT;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.turnat.TurnAT.models.entities.Cliente;
import com.turnat.TurnAT.repositories.IClienteRepository;

@Component
public class PasswordEncryptorRunner implements CommandLineRunner {

    private final IClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;

    public PasswordEncryptorRunner(IClienteRepository clienteRepository, PasswordEncoder passwordEncoder) {
        this.clienteRepository = clienteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        var clientes = clienteRepository.findAll();
        for (Cliente c : clientes) {
            String pass = c.getPassword();
            if (!pass.startsWith("$2a$")) { // si no está encriptada con BCrypt
                String encrypted = passwordEncoder.encode(pass);
                c.setPassword(encrypted);
                clienteRepository.save(c);
                System.out.println("Contraseña encriptada para: " + c.getEmail());
            }
        }
    }
}
*/
