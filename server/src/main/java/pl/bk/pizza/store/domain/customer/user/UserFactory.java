package pl.bk.pizza.store.domain.customer.user;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.bk.pizza.store.domain.customer.address.Address;
import pl.bk.pizza.store.domain.customer.telephone.Telephone;
import pl.bk.pizza.store.domain.validator.EmailValidator;

import static pl.bk.pizza.store.domain.validator.customer.AddressValidator.validateAddress;
import static pl.bk.pizza.store.domain.validator.customer.TelephoneValidator.validateTelephone;
import static pl.bk.pizza.store.domain.validator.customer.UserValidator.validateName;
import static pl.bk.pizza.store.domain.validator.customer.UserValidator.validateSurname;

@Service
@AllArgsConstructor
public class UserFactory
{
    private final EmailValidator emailValidator;
    
    public User create(String email, String name, String surname, String password,
        Address address, Telephone telephone, String role)
    {
        emailValidator.validateEmail(email);
        validateName(name);
        validateSurname(surname);
        validateAddress(address);
        validateTelephone(telephone);
        
        if(role == null)
        {
            role = "USER";
        }
        
        return new User(
            email,
            name,
            surname,
            new BCryptPasswordEncoder().encode(password),
            address,
            telephone,
            UserStatus.ACTIVE,
            new Points(0),
            role
        );
    }
}
