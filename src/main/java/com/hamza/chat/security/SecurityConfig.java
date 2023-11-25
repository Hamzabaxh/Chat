package com.hamza.chat.security;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import javax.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

            .cors().configurationSource(new CorsConfigurationSource() {
                @Override
                public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                    config.setAllowedMethods(Collections.singletonList("*"));
                    config.setAllowCredentials(true);
                    config.setAllowedHeaders(Collections.singletonList("*"));
                    config.setExposedHeaders(Arrays.asList("Authorization"));
                    config.setMaxAge(3600L);
                    return config;
                }
            }).and()

            .authorizeRequests(authorizeRequests ->
                authorizeRequests
                    .antMatchers("/api/all/**").hasAnyAuthority("ADMIN", "USER")
                    .antMatchers("/api/getbyid/**").hasAnyAuthority("ADMIN", "USER")
                    .antMatchers(HttpMethod.POST, "/api/addprod/**").hasAuthority("ADMIN")
                    .antMatchers(HttpMethod.PUT, "/api/updateprod/**").hasAuthority("ADMIN")
                    .antMatchers(HttpMethod.DELETE, "/api/delprod/**").hasAuthority("ADMIN")
                    .antMatchers("/cat/**").hasAnyAuthority("ADMIN", "USER")
                    .anyRequest().authenticated()
            )
            .addFilterBefore(new JWTAuthorizationFilter(), BasicAuthenticationFilter.class);

        return http.build();
    }
}
