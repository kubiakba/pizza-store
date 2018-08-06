package pl.bk.pizza.store.infrastructure.security.jwt;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class CustomReactiveAuthenticationManager implements ReactiveAuthenticationManager
{
    @Override
    public Mono<Authentication> authenticate(Authentication authentication)
    {
        return Mono.just(authentication);
    }
}