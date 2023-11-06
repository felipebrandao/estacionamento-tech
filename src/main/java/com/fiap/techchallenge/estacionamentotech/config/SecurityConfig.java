package com.fiap.techchallenge.estacionamentotech.config;

import com.fiap.techchallenge.estacionamentotech.security.JwtAuthenticationFilter;
import com.fiap.techchallenge.estacionamentotech.security.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationConfiguration authenticationConfiguration;

    public SecurityConfig(UserDetailsService userDetailsService, JwtTokenProvider jwtTokenProvider, AuthenticationConfiguration authenticationConfiguration) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationConfiguration = authenticationConfiguration;
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/usuario/cadastro/**").permitAll()
                        .requestMatchers("/enviar-email").permitAll()
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        .requestMatchers("/veiculo").hasAuthority("COMUM")
                        .requestMatchers("/estacionamento/estacionar").hasAuthority("COMUM")
                        .requestMatchers("/estacionamento/estender-horas/**").hasAuthority("COMUM")
                        .requestMatchers("/estacionamento/locais/listar").hasAuthority("COMUM")

                        .requestMatchers("/multa").hasAuthority("FISCAL")
                        .requestMatchers("/estacionamento/locais").hasAuthority("FISCAL")
                        .anyRequest().authenticated());


        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider, userDetailsService, authenticationManager()), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}