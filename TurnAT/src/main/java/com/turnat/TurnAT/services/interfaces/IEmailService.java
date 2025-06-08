package com.turnat.TurnAT.services.interfaces;

public interface IEmailService {
    void enviarCorreo(String para, String asunto, String cuerpo);
}