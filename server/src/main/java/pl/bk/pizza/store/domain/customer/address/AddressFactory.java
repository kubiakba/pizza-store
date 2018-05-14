package pl.bk.pizza.store.domain.customer.address;

import org.springframework.stereotype.Component;

import static pl.bk.pizza.store.domain.validator.customer.AddressValidator.validateCity;
import static pl.bk.pizza.store.domain.validator.customer.AddressValidator.validatePostCode;
import static pl.bk.pizza.store.domain.validator.customer.AddressValidator.validateStreet;
import static pl.bk.pizza.store.domain.validator.customer.AddressValidator.validateStreetNumber;

@Component
public class AddressFactory
{
    public Address create(String city, String street, String streetNumber, String postCode)
    {
        validateCity(city);
        validateStreet(street);
        validateStreetNumber(streetNumber);
        validatePostCode(postCode);
        return new Address(city, street, streetNumber, postCode);
    }
}
