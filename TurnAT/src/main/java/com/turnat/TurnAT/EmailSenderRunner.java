package com.turnat.TurnAT;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.turnat.TurnAT.models.entities.Cliente;
import com.turnat.TurnAT.models.entities.Direccion;
import com.turnat.TurnAT.repositories.IClienteRepository;
import com.turnat.TurnAT.services.interfaces.IEmailService;

@Component
public class EmailSenderRunner implements CommandLineRunner {

    private final IClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;
    private final IEmailService emailService;

    public EmailSenderRunner(IClienteRepository clienteRepository, PasswordEncoder passwordEncoder, IEmailService emailService) {
        this.clienteRepository = clienteRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    @Override
    public void run(String... args) {
        // Crear la dirección
        Direccion direccion = new Direccion("CABA", "Calle Inventada", 456);
/*
        // Crear el cliente con el constructor completo
        Cliente nuevoCliente = new Cliente(
                "Santiago",                        // nombre
                passwordEncoder.encode("test1234"), // password encriptada
                "Serrano",                         // apellido
                455646518,                          // DNI
                "santiagogonzaloserrano@gmail.com",// email
                direccion,                         // dirección
                "1122334455"                       // teléfono
        );

        // Guardar el cliente
        clienteRepository.save(nuevoCliente);
*/
        // Enviar correo de bienvenida
        String asunto = "¡Bienvenido a TurnAT!";
        String cuerpo = "Hola Santiago, este es un correo de prueba enviado desde el sistema de turnos. ¡Todo está funcionando correctamente!";
        emailService.enviarCorreo("santiagogonzaloserrano@gmail.com", asunto, cuerpo);

    }
}
