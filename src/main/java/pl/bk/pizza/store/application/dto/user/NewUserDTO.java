package pl.bk.pizza.store.application.dto.user;

import lombok.Data;

@Data
public class NewUserDTO {

    private String email;
    private String password;
    private String name;
    private String surname;
    private AddressDTO address;
    private TelephoneDTO telephone;
}
