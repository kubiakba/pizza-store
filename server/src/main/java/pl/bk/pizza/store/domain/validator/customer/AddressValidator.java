package pl.bk.pizza.store.domain.validator.customer;

import pl.bk.pizza.store.domain.customer.Address;
import pl.bk.pizza.store.domain.exception.InvalidEntityException;

import static org.apache.commons.lang.StringUtils.isBlank;
import static pl.bk.pizza.store.domain.exception.ErrorCode.EMPTY_ADDRESS;
import static pl.bk.pizza.store.domain.exception.ErrorCode.EMPTY_CITY;
import static pl.bk.pizza.store.domain.exception.ErrorCode.EMPTY_POSTCODE;
import static pl.bk.pizza.store.domain.exception.ErrorCode.EMPTY_STREET;
import static pl.bk.pizza.store.domain.exception.ErrorCode.EMPTY_STREET_NUMBER;
import static pl.bk.pizza.store.domain.exception.Preconditions.check;

public class AddressValidator
{
    public static void validateAddress(Address address)
    {
        check(address == null, () -> new InvalidEntityException("Address is null.", EMPTY_ADDRESS));
    }
    
    public static void validateCity(String city)
    {
        check(isBlank(city), () -> new InvalidEntityException(("City is null or empty."), EMPTY_CITY));
    }
    
    public static void validateStreet(String street)
    {
        check(isBlank(street), () -> new InvalidEntityException(("Street is null or empty."), EMPTY_STREET));
    }
    
    public static void validateStreetNumber(String streetNumber)
    {
        check(isBlank(streetNumber), () -> new InvalidEntityException(
            ("StreetNumber is null or empty."),
            EMPTY_STREET_NUMBER
        ));
    }
    
    public static void validatePostCode(String postCode)
    {
        check(isBlank(postCode), () -> new InvalidEntityException(
            ("PostCode is null or empty."),
            EMPTY_POSTCODE
        ));
    }
}
