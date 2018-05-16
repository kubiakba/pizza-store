package pl.bk.pizza.store.domain.customer;

import lombok.Getter;

import static pl.bk.pizza.store.domain.validator.customer.AddressValidator.validateCity;
import static pl.bk.pizza.store.domain.validator.customer.AddressValidator.validatePostCode;
import static pl.bk.pizza.store.domain.validator.customer.AddressValidator.validateStreet;
import static pl.bk.pizza.store.domain.validator.customer.AddressValidator.validateStreetNumber;

@Getter
public class Address
{
    private final String city;
    private final String street;
    private final String streetNumber;
    private final String postCode;
    
    public Address(String city, String street, String streetNumber, String postCode)
    {
        validateCity(city);
        validateStreet(street);
        validateStreetNumber(streetNumber);
        validatePostCode(postCode);
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
        this.postCode = postCode;
    }
}
