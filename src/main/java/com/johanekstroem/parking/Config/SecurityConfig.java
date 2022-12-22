package com.johanekstroem.parking.Config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {
  
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
        .csrf().disable()
        .cors()
        .and()//Allow requests from foreign domains
        .formLogin().disable()
        .logout().disable()
        .sessionManagement().disable() //Disable session cookies.
        .authorizeHttpRequests()
        //.requestMatchers("/").anonymous() //Index landing page is open for everyone regardless auth
        .requestMatchers("/api/**").authenticated()
        .requestMatchers("/**").anonymous()
        .anyRequest().denyAll() // Closes every other end point that's not explicit handled above
        .and()
        .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
        .build();
  }
  
  @Bean
  public JwtDecoder jwtDecoder() {
    return NimbusJwtDecoder
        .withJwkSetUri("https://fungover.org/auth/.well-known/jwks.json")
        .jwsAlgorithm(SignatureAlgorithm.ES256)
        .build();
  }
    
  @Bean
  CorsConfigurationSource corsConfigurationSource() {
  CorsConfiguration configuration = new CorsConfiguration();
  configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
  configuration.setAllowedMethods(Arrays.asList("GET","POST","PATCH"));
  configuration.setAllowCredentials(true);
  configuration.setAllowedHeaders(Arrays.asList("*"));
  configuration.setExposedHeaders(Arrays.asList("X-Get-Header"));
  configuration.setMaxAge(3600L);
  UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
  source.registerCorsConfiguration("/**", configuration);
  return source;
} 
}
