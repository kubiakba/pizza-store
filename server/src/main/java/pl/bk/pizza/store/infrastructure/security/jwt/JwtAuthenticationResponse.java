package pl.bk.pizza.store.infrastructure.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class JwtAuthenticationResponse
{
    private String username;
    private String token;
}
