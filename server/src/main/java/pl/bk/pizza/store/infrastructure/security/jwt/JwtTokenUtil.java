package pl.bk.pizza.store.infrastructure.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.bk.pizza.store.domain.customer.user.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static pl.bk.pizza.store.domain.service.NowProvider.now;

@Component
public class JwtTokenUtil
{
    @Value("${jwt.secret}")
    private String secret;
    
    String getUsernameFromToken(String token)
    {
        return Jwts
            .parser()
            .setSigningKey(secret)
            .parseClaimsJws(token)
            .getBody()
            .get("username", String.class);
    }
    
    String getAuthoritiesFromToken(String token)
    {
        return Jwts
            .parser()
            .setSigningKey(secret)
            .parseClaimsJws(token)
            .getBody()
            .get("roles", String.class);
    }
    
    public String generateToken(User user)
    {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getEmail());
        claims.put("roles", user.getRole());
        
        return Jwts.builder()
                   .setIssuer("pizza-store")
                   .setSubject("bk")
                   .setClaims(claims)
                   .setIssuedAt(Date.from(now().toInstant()))
                   .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                   .signWith(SignatureAlgorithm.HS512, secret)
                   .compact();
    }
}