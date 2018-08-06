package pl.bk.pizza.store.application.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AddressDTO
{
    private String city;
    private String street;
    private String streetNumber;
    private String postCode;
}
