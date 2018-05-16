package pl.bk.pizza.store.application.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.bk.pizza.store.domain.customer.user.UserStatus;

@AllArgsConstructor
@Getter
public class UserDTO
{
    private String email;
    private String name;
    private String surname;
    private AddressDTO address;
    private TelephoneDTO telephone;
    private UserStatus status;
    private int points;
}
