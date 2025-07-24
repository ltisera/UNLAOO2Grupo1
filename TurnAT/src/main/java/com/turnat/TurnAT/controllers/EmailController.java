package com.turnat.TurnAT.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.turnat.TurnAT.services.interfaces.IEmailService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/correo")
@Tag(name = "Email", description = "envia el mail")
public class EmailController {

    @Autowired
    private IEmailService emailService;

    @PostMapping("/enviar")
    public String enviarCorreo(
            @RequestParam String para,
            @RequestParam String asunto,
            @RequestParam String cuerpo) {

        emailService.enviarCorreo(para, asunto, cuerpo);
        return "Correo enviado a " + para;
    }
}