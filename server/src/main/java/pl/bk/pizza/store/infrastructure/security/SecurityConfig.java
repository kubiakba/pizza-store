package pl.bk.pizza.store.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import pl.bk.pizza.store.infrastructure.security.jwt.JwtAuthenticationWebFilter;
import pl.bk.pizza.store.infrastructure.security.jwt.UnauthorizedEntryPoint;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.PATCH;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

@EnableWebFluxSecurity
public class SecurityConfig
{
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http, UnauthorizedEntryPoint entryPoint, JwtAuthenticationWebFilter webFilter)
    {
        return http
            .authorizeExchange().pathMatchers("/*").permitAll()
            .pathMatchers(GET, "/orders/*").permitAll()
            .pathMatchers(PUT, "/orders/*/to-realization").permitAll()
            .pathMatchers(PUT, "/orders/*/users/*").permitAll()
            .pathMatchers(PUT, "/orders/*/*").permitAll()
            .pathMatchers(GET, "/products/kebabs").permitAll()
            .pathMatchers(GET, "/products/available").permitAll()
            .pathMatchers(GET, "/products").permitAll()
            .pathMatchers(GET, "/products/pizzas").permitAll()
            .pathMatchers(GET, "/products/pizzaToppings").permitAll()
            .pathMatchers(GET, "/products/*").permitAll()
            .pathMatchers(POST, "/users").permitAll()
            .pathMatchers(POST, "/users/*").permitAll()
            .pathMatchers(GET, "/users/*").permitAll()
            .pathMatchers(GET, "/users/*/bonus").permitAll()
            .pathMatchers(POST, "/orders").permitAll()
            .pathMatchers(PATCH, "/users/*/deactivate").permitAll()
            .anyExchange().hasRole("ADMIN")
            .and()
            .exceptionHandling()
            .authenticationEntryPoint(entryPoint)
            .and()
            .addFilterAt(webFilter, SecurityWebFiltersOrder.AUTHENTICATION)
            .csrf().disable()
            .httpBasic().disable()
            .build();
    }
    
    @Bean
    PasswordEncoder delegatingPasswordEncoder()
    {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        return new DelegatingPasswordEncoder("bcrypt", encoders);
    }
}
