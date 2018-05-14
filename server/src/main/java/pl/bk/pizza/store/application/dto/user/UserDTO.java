package pl.bk.pizza.store.application.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserDTO
{
    private String email;
    private String name;
    private String surname;
    private AddressDTO address;
    private TelephoneDTO telephone;
    private int points;
}
