package pl.bk.pizza.store.domain.customer.user;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.bk.pizza.store.domain.customer.Address;
import pl.bk.pizza.store.domain.customer.Telephone;

import static pl.bk.pizza.store.domain.validator.EmailValidator.validateEmail;
import static pl.bk.pizza.store.domain.validator.customer.AddressValidator.validateAddress;
import static pl.bk.pizza.store.domain.validator.customer.TelephoneValidator.validateTelephone;
import static pl.bk.pizza.store.domain.validator.customer.UserValidator.validateName;
import static pl.bk.pizza.store.domain.validator.customer.UserValidator.validateSurname;

@Service
@AllArgsConstructor
public class UserFactory
{
    private static final String USER = "USER";
    
    public User create(String email, String name, String surname, String password,
        Address address, Telephone telephone)
    {
        validateEmail(email);
        validateName(name);
        validateSurname(surname);
        validateAddress(address);
        validateTelephone(telephone);
        
        return new User(
            email,
            name,
            surname,
            "{bcrypt}" + new BCryptPasswordEncoder().encode(password),
            address,
            telephone,
            UserStatus.ACTIVE,
            0,
            USER
        );
    }
}
