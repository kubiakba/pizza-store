package pl.bk.pizza.store.domain.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import pl.bk.pizza.store.domain.customer.user.User;
import pl.bk.pizza.store.domain.customer.user.UserRepository;
import pl.bk.pizza.store.domain.exception.ErrorCode;
import pl.bk.pizza.store.domain.exception.MissingEntityException;

import static pl.bk.pizza.store.domain.exception.Preconditions.check;

@Component
@AllArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService
{
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String email)
    {
        return userRepository
            .findById(email)
            .doOnNext(this::checkIfUserExists)
            .map(user -> org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole())
                .build())
            .block();
    }
    
    private final void checkIfUserExists(User user)
    {
        check(user == null, () -> new MissingEntityException(
            "User with email: " + user.getEmail() + " does not exists.",
            ErrorCode.MISSING_USER
        ));
    }
}
