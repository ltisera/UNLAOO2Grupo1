package com.turnat.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HolaControl {

    @GetMapping("/hola")
    public String hola() {
        return "Hola desde Spring Boot!";
    }
}