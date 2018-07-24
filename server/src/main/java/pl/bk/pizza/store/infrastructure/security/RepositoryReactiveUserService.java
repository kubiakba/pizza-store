package pl.bk.pizza.store.infrastructure.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import pl.bk.pizza.store.domain.customer.user.UserRepository;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Component
public class RepositoryReactiveUserService implements ReactiveUserDetailsService
{
    private final UserRepository repository;
    
    public Mono<UserDetails> findByUsername(String username)
    {
        return repository.findById(username).
            map(user ->
                    org.springframework.security.core.userdetails.User
                        .withUsername(user.getEmail())
                        .roles(user.getRole())
                        .password(user.getPassword()).build());
    }
}
