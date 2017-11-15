package pl.bk.pizza.store.application.dto.user;

import lombok.Data;

@Data
public class AddressDTO {

    private String city;
    private String street;
    private String streetNumber;
    private String postCode;
}
