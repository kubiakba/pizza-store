package pl.bk.pizza.store.infrastructure.security.jwt;

import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken
{
    private final String username;
    private final String token;
    
    JwtAuthenticationToken(String token, String username, Collection<? extends GrantedAuthority> authorities)
    {
        super(username, null, authorities);
        this.username = username;
        this.token = token;
    }
    
    @Override
    public Object getCredentials()
    {
        return null;
    }
    
    @Override
    public Object getPrincipal()
    {
        return username;
    }
}
