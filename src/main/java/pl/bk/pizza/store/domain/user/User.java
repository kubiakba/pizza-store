package pl.bk.pizza.store.domain.user;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.data.annotation.Id;

import pl.bk.pizza.store.domain.exception.InvalidEntityException;

import static org.apache.commons.lang.StringUtils.isBlank;
import static pl.bk.pizza.store.domain.exception.ErrorCode.*;
import static pl.bk.pizza.store.domain.exception.Preconditions.check;

public class User {

    @Id
    private final String email;
    private String password;
    private String name;
    private String surname;
    private Address address;
    private Telephone telephone;
    private Integer points;
    private UserStatus status;

    User(String email, String name, String surname, String password, Address address, Telephone telephone) {
        points = 0;
        status = UserStatus.ACTIVE;
        validateEmail(email);
        validateName(name);
        validateSurname(surname);
        validateAddress(address);
        validateTelephone(telephone);
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.address = address;
        this.telephone = telephone;
    }

    private void validateEmail(String email) {
        EmailValidator validator = EmailValidator.getInstance();
        check((isBlank(email)), () -> new InvalidEntityException(("Email is null or empty."), EMPTY_USER_EMAIL));
        check((!validator.isValid(email)), () -> new InvalidEntityException(("Error while validating email."), INVALID_EMAIL));
    }

    private void validateName(String name) {
        check(isBlank(name),() -> new InvalidEntityException("Name is null.", EMPTY_USER_NAME));
    }

    private void validateSurname(String surname) {
        check(isBlank(surname),() -> new InvalidEntityException("Surname is null or empty.", EMPTY_USER_SURNAME));
    }

    private void validateAddress(Address address) {
        check(address == null,() -> new InvalidEntityException("Address is null.", EMPTY_ADDRESS));
    }

    private void validateTelephone(Telephone telephone) {
        check((telephone == null),() -> new InvalidEntityException("Telephone is null.", EMPTY_TELEPHONE_NUMBER));
    }

    public void deactivateUser(){
        status = UserStatus.INACTIVE;
    }

    public void changeSurname(String surname) {
        this.surname = surname;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changeAddress(Address address) {
        this.address.changeCity(address.getCity());
        this.address.changeStreet(address.getStreet());
        this.address.changeStreetNumber(address.getStreetNumber());
        this.address.changePostCode(address.getPostCode());
    }

    public void changeTelephone(Telephone telephone) {
        this.telephone = telephone;
    }

    public Integer getPoints(){
        return points;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() { return email; }

    public String getName() { return name; }

    public String getSurname() { return surname; }

    public Address getAddress() { return address; }

    public Telephone getTelephone() { return telephone; }
}
