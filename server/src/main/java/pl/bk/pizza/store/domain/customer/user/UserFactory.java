package pl.bk.pizza.store.domain.customer.user;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static pl.bk.pizza.store.domain.customer.user.UserStatus.ACTIVE;
import static pl.bk.pizza.store.domain.validator.EmailValidator.validateEmail;

@Service
@AllArgsConstructor
public class UserFactory
{
    private static final String USER = "USER";
    private PasswordEncoder encoder;
    
    public User create(String email, String password)
    {
        validateEmail(email);
        
        return new User(
            email,
            encoder.encode(password),
            ACTIVE,
            0,
            USER
        );
    }
}
