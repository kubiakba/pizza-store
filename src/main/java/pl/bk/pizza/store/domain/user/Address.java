package pl.bk.pizza.store.domain.user;

import pl.bk.pizza.store.domain.exception.InvalidEntityException;

import static org.apache.commons.lang.StringUtils.isBlank;
import static pl.bk.pizza.store.domain.exception.ErrorCode.*;
import static pl.bk.pizza.store.domain.exception.Preconditions.check;

public class Address {

    private String city;
    private String street;
    private String streetNumber;
    private String postCode;

    public Address(String city, String street, String streetNumber, String postCode) {
        validateCity(city);
        validateStreet(street);
        validateStreetNumber(streetNumber);
        validatePostCode(postCode);
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
        this.postCode = postCode;
    }

    private void validateCity(String city) {
        check(isBlank(city), () -> new InvalidEntityException(("City is null or empty."), EMPTY_CITY));
    }

    private void validateStreet(String street) {
        check(isBlank(street), () -> new InvalidEntityException(("Street is null or empty."), EMPTY_STREET));
    }

    private void validateStreetNumber(String streetNumber) {
        check(isBlank(streetNumber), () -> new InvalidEntityException(("StreetNumber is null or empty."),
            EMPTY_STREET_NUMBER));
    }

    private void validatePostCode(String postCode) {
        check(isBlank(postCode), () -> new InvalidEntityException(("PostCode is null or empty."),
            EMPTY_POSTCODE));
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getPostCode() {
        return postCode;
    }

    public void changeCity(String city) {
        validateCity(city);
        this.city = city;
    }

    public void changeStreet(String street) {
        validateStreet(street);
        this.street = street;
    }

    public void changeStreetNumber(String streetNumber) {
        validateStreetNumber(streetNumber);
        this.streetNumber = streetNumber;
    }

    public void changePostCode(String postCode) {
        validatePostCode(postCode);
        this.postCode = postCode; }
}
