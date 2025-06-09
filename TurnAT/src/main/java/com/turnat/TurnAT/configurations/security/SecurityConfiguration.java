package com.turnat.TurnAT.configurations.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.turnat.TurnAT.services.implementations.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	@Autowired
	private CustomUserDetailsService userDetailsService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
         	.userDetailsService(userDetailsService)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/css/**", "/js/**", "/img/**", "/login", "/registro", "/","/cliente/registro-cliente").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/empleado/**").hasRole("EMPLEADO")
                .requestMatchers("/cliente/**").hasRole("CLIENTE")
                .anyRequest().authenticated()
            )
            .formLogin(login -> login
                    .loginPage("/login")
                    .permitAll()
                    .successHandler((request, response, authentication) -> {
                        // Redirección según el rol
                        var roles = authentication.getAuthorities().stream()
                                .map(Object::toString).toList();
                        if (roles.contains("ROLE_ADMIN")) {
                            response.sendRedirect("/admin/blancoAdmin");
                        } else if (roles.contains("ROLE_EMPLEADO")) {
                            response.sendRedirect("/empleado/blancoEmp");
                        } else {
                            response.sendRedirect("/cliente/indexCliente");
                        }
                    })
                )
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(7);
    }
}