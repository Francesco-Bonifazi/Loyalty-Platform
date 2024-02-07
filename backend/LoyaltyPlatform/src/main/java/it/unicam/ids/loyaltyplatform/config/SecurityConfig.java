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

    private final String[] AUTH_WHITELIST = {
            "/dashboard/auth/**",
            "/dashboard/healthcheck",
            "/dashboard/newutente",
            "/dashboard/viewfattura/**",
            "/dashboard/viewfatture/**",
            "/dashboard/viewcatalogo/**"
    };
    private final String[] PF_AUTH = {
            "/dashboard/newprogram",
            "/dashboard/editprogram"
    };
    private final String[] PV_AUTH = {
            "/dashboard/newpuntovendita"
    };
    private final String[] USER_REMOVE_AUTH = {
            "/dashboard/removeutente"
    };
    private final String[] TESSERE_VIEW = {
            "/dashboard/viewiscritti"
    };
    private final String[] SUBSCRIBE_AUTH = {
            "/dashboard/newtessera"
    };
    private final String[] PV_SEARCH = {
            "/dashboard/viewpuntovendita"
    };
    private final String[] INFO_VIEW = {
            "/dashboard/viewinfo"
    };
    private final String[] UNSUBSCRIBE = {
            "/dashboard/removetessera",
    };
    private final String[] INFO_UPDATE = {
            "/dashboard/updateaccount"
    };
    private final String[] DIPENDENTI_VIEW = {
            "/dashboard/viewdipendenti"
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
                        .requestMatchers(PF_AUTH).hasAuthority("PF_AUTH")
                        .requestMatchers(PV_AUTH).hasAuthority("PV_AUTH")
                        .requestMatchers(USER_REMOVE_AUTH).hasAuthority("USER_REMOVE_AUTH")
                        .requestMatchers(TESSERE_VIEW).hasAuthority("TESSERE_VIEW")
                        .requestMatchers(SUBSCRIBE_AUTH).hasAuthority("SUBSCRIBE_AUTH")
                        .requestMatchers(PV_SEARCH).hasAuthority("PV_SEARCH")
                        .requestMatchers(INFO_VIEW).hasAuthority("INFO_VIEW")
                        .requestMatchers(UNSUBSCRIBE).hasAuthority("UNSUBSCRIBE")
                        .requestMatchers(INFO_UPDATE).hasAuthority("INFO_UPDATE")
                        .requestMatchers(DIPENDENTI_VIEW).hasAuthority("DIPENDENTI_VIEW")
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}

