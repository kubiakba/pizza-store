package pl.bk.pizza.store.application.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NewUserDTO
{
    private String email;
    private String password;
    private String name;
    private String surname;
    private AddressDTO address;
    private TelephoneDTO telephone;
    private String role;
}
