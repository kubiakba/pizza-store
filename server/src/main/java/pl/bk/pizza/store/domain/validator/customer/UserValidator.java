package pl.bk.pizza.store.domain.validator.customer;

import pl.bk.pizza.store.domain.exception.InvalidEntityException;

import static org.apache.commons.lang.StringUtils.isBlank;
import static pl.bk.pizza.store.domain.exception.ErrorCode.EMPTY_USER_NAME;
import static pl.bk.pizza.store.domain.exception.ErrorCode.EMPTY_USER_SURNAME;
import static pl.bk.pizza.store.domain.exception.Preconditions.check;

public class UserValidator
{
    public static void validateName(String name)
    {
        check(isBlank(name), () -> new InvalidEntityException("Name is empty or null.", EMPTY_USER_NAME));
    }
    
    public static void validateSurname(String surname)
    {
        check(isBlank(surname), () -> new InvalidEntityException("Surname is null or empty.", EMPTY_USER_SURNAME));
    }
}
