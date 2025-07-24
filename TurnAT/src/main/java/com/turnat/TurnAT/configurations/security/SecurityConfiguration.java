package com.turnat.TurnAT.configurations.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.turnat.TurnAT.configurations.security.jwt.JwtFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	 private final UserDetailsService userDetailsService;
	    private final JwtFilter jwtFilter;

	    public SecurityConfiguration(UserDetailsService userDetailsService, JwtFilter jwtFilter) {
	        this.userDetailsService = userDetailsService;
	        this.jwtFilter = jwtFilter;
	    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .csrf(csrf -> csrf.disable())  // Desactivar CSRF
         	.userDetailsService(userDetailsService)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(  "/css/**", "/js/**", "/img/**",	   // Recursos estáticos
                        			"/", "/login", "/registro",       // Páginas públicas
                        			"/cliente/registro-cliente",     // Registro de clientes
                        			"/swagger-ui/**",				//Para el swagger
                                    "/swagger-ui.html",
                                    "/v3/api-docs/**",
                                    "/api/login"				//Para el login rest con Swagger
                        ).permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/empleado/**").hasRole("EMPLEADO")
                .requestMatchers("/cliente/**").hasRole("CLIENTE")
                .requestMatchers("/api/login").permitAll()
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
                            response.sendRedirect("/admin/indexAdmin");
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
        // Filtro para JWT
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    
    
    
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(7);
    }
}