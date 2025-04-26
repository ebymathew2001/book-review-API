package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
    private final CustomUserDetailsService userDetailsService;

    
    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authorize -> authorize
                // Public endpoints - allow read access to books and reviews
            		 .requestMatchers(HttpMethod.GET, "/books", "/books/*", "/books/*/reviews", "/reviews").permitAll()
            		    
            		    // Admin-only access to modifying books
            		    .requestMatchers(HttpMethod.POST, "/books").hasRole("ADMIN")
            		    .requestMatchers(HttpMethod.PUT, "/books/*").hasRole("ADMIN")
            		    .requestMatchers(HttpMethod.DELETE, "/books/*").hasRole("ADMIN")
            		    
            		    // Admin-only access to users and other admin stuff
            		    .requestMatchers("/admin/**", "/users/**").hasRole("ADMIN")    // Only admins can manage users
                
                // All other endpoints require authentication
                .anyRequest().authenticated()
            )
            .httpBasic(httpBasic -> {})
            .userDetailsService(userDetailsService);
            
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}