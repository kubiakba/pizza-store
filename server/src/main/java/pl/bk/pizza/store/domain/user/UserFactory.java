package pl.bk.pizza.store.domain.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserFactory {

    private final static String USER_ROLE = "USER";

    public User createUser(String email, String name, String surname, String password, Address address, Telephone telephone, String role) {
        if(role == null){
            role = USER_ROLE;
        }
        return new User(email, name, surname, new BCryptPasswordEncoder().encode(password), address, telephone,
            role);
    }
}
