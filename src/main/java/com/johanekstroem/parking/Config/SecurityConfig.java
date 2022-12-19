package com.johanekstroem.parking.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
  
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
        .csrf().disable()
        .cors().disable() //Allow requests from foreign domains
        .formLogin().disable()
        .logout().disable()
        .sessionManagement().disable() //Disable session cookies.
        .authorizeHttpRequests()
        .requestMatchers("/").anonymous() //Index landing page is open for everyone regardless auth
        .requestMatchers("/*").anonymous()
        .requestMatchers("/login").anonymous()
        .requestMatchers("/createuser").anonymous()
        .requestMatchers("/handleNewUserForm").anonymous()
        .requestMatchers("/js/*").anonymous()
        .requestMatchers("/api/**").authenticated()
        .anyRequest().denyAll() // Closes every other end point that's not explicit handled above
        .and()
        .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
        .build();
  }
  
  @Bean
    public JwtDecoder jwtDecoder(){
        return NimbusJwtDecoder
                .withJwkSetUri("https://fungover.org/auth/.well-known/jwks.json")
                .jwsAlgorithm(SignatureAlgorithm.ES256)
                .build();
    }
}
