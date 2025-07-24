package com.turnat.TurnAT.configurations.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.cglib.core.internal.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "clave-secreta-super-larga-para-jwt-que-tenga-al-menos-32-bytes";
    
    
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(Base64.getEncoder().encodeToString(SECRET_KEY.getBytes()));
        return Keys.hmacShaKeyFor(keyBytes);
    }
    // Extraer el username (subject) del token
    public String extraerUsername(String token) {
        return extraerClaim(token, Claims::getSubject);
    }

    // Extraer la fecha de expiración
    public Date extractExpiration(String token) {
        return extraerClaim(token, Claims::getExpiration);
    }

    // Extraer un solo claim (campo) del token
    public <T> T extraerClaim(String token, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(extractAllClaims(token));
    }


    // Obtener todos los claims
    public Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Verificar si expiró
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Generar token
    public String generarToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();

        String rol = userDetails.getAuthorities().stream()
                      .findFirst()
                      .map(auth -> auth.getAuthority().replace("ROLE_", ""))  // ADMIN, CLIENTE, EMPLEADO
                      .orElse("CLIENTE");

        claims.put("role", rol);
        return crearToken(claims, userDetails.getUsername());
    }

    // Crear token JWT con claims y subject
    private String crearToken(Map<String, Object> claims, String subject) {
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hs
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    // Validar que el token es del usuario indicado y no está vencido
    public boolean validarToken(String token, UserDetails userDetails) {
        final String username = extraerUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}


