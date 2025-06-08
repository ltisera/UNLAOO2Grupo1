package com.turnat.TurnAT.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.turnat.TurnAT.services.interfaces.IEmailService;

@Service
public class EmailServiceImp implements IEmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void enviarCorreo(String para, String asunto, String cuerpo) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(para);
        mensaje.setSubject(asunto);
        mensaje.setText(cuerpo);
        mensaje.setFrom("oo2grupo01@gmail.com"); // Cambia esto a tu dirección de correo

        try {
            mailSender.send(mensaje);
            System.out.println("Correo enviado correctamente!");
        } catch (Exception e) {
            System.err.println("Error al enviar el correo: " + e.getMessage());
        }
    }
    
}