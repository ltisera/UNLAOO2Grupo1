package com.turnat.controlador;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HolaControl {

    @GetMapping("/")
    public String hola() {
        return "Hola desde Spring Boot!";
    }
}