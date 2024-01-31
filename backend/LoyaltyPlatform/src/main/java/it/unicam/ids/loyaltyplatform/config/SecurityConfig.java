package it.unicam.ids.loyaltyplatform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    private static final String[] AUTH_WHITELIST = {
            "/dashboard/auth/**",
            "/dashboard/healthcheck"
    };
    private static final String[] AUTH_TITOLARE = {

            "/dashboard/newpuntovendita",
            "/dashboard/newprogram",
            "/dashboard/editprogram"
    };
    private static final String[] AUTH_ADMIN = {
            "/dashboard/viewiscritti",
            "/dashboard/removeutente"
    };
    private static final String[] AUTH_CASSIERE = {
            "/dashboard/newutente",
            "/dashboard/newtessera",
            "/dashboard/visualizzaClienti"
    };
    private static final String[] AUTH_USER = {
            "/dashboard/viewpuntovendita",
            "/dashboard/removetessera",
            "/dashboard/updateaccount",
            "/dashboard/viewinfo"
    };



    public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter, AuthenticationProvider authenticationProvider){

        this.jwtAuthFilter = jwtAuthFilter;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(AUTH_WHITELIST).permitAll()
                        .requestMatchers(AUTH_ADMIN).hasAuthority("AMMINISTRATORE")
                        .requestMatchers(AUTH_CASSIERE).hasAuthority("CASSIERE")
                        .requestMatchers(AUTH_TITOLARE).hasAuthority("TITOLARE")
                        .requestMatchers(AUTH_USER).hasAuthority("UTENTE")
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}

