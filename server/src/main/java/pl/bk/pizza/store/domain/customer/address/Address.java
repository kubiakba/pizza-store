package pl.bk.pizza.store.domain.customer.address;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Address
{
    private final String city;
    private final String street;
    private final String streetNumber;
    private final String postCode;
}
