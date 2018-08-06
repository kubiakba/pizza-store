package pl.bk.pizza.store.infrastructure.security.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@Component
public class JwtServerExchangeConverter implements Function<ServerWebExchange, Mono<Authentication>>
{
    private final JwtTokenUtil jwtTokenUtil;
    
    @Value("${jwt.header}")
    private String tokenHeader;
    
    @Value("${jwt.prefix}")
    private String bearerPrefix;
    
    public JwtServerExchangeConverter(JwtTokenUtil jwtTokenUtil)
    {
        this.jwtTokenUtil = jwtTokenUtil;
    }
    
    @Override
    public Mono<Authentication> apply(ServerWebExchange exchange)
    {
        try
        {
            String token = null;
            String username = null;
            List<GrantedAuthority> roles = null;
            
            String authorizationHeader = exchange
                .getRequest()
                .getHeaders()
                .getFirst(tokenHeader);
            
            if(authorizationHeader != null && authorizationHeader.startsWith(bearerPrefix + " "))
            {
                token = authorizationHeader.substring(7);
            }
            
            if(token != null)
            {
                username = jwtTokenUtil.getUsernameFromToken(token);
                roles = Arrays.stream(jwtTokenUtil.getAuthoritiesFromToken(token)
                                                  .split(","))
                              .map(SimpleGrantedAuthority::new)
                              .collect(toList());
            }
            
            if(username != null)
            {
                return Mono.just(new JwtAuthenticationToken(token, username, roles));
            }
            return Mono.empty();
        }
        catch(Exception e)
        {
            throw new BadCredentialsException("Invalid token: ", e);
        }
    }
}
