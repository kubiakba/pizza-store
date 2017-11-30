package pl.bk.pizza.store.domain.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserFactory {

    public User createUser(String email, String name, String surname, String password, Address address, Telephone telephone) {
        return new User(email, name, surname, new BCryptPasswordEncoder().encode(password), address, telephone);
    }
}
