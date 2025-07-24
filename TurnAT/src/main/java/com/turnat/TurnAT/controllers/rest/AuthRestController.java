package com.turnat.TurnAT.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turnat.TurnAT.configurations.security.jwt.JwtUtil;
import com.turnat.TurnAT.dto.LoginDTO;
import com.turnat.TurnAT.services.implementations.CustomUserDetailsService;

import io.jsonwebtoken.lang.Collections;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@Tag(name = "Login", description ="Para probar el login con Swagger" )
public class AuthRestController {
	

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            // Autenticar con Spring Security
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.email(), loginDTO.password()
                    )
            );

            // Obtener el usuario autenticado
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // Generar el token
            String jwt = jwtUtil.generarToken(userDetails);

            // Retornar token en JSON
            return ResponseEntity.ok().body("{\"token\": \"" + jwt + "\"}");
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
    }
	

}
