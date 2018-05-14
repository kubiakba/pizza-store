package pl.bk.pizza.store.domain.validator;

import org.springframework.stereotype.Component;
import pl.bk.pizza.store.domain.exception.InvalidEntityException;

import static org.apache.commons.lang.StringUtils.isBlank;
import static pl.bk.pizza.store.domain.exception.ErrorCode.EMPTY_USER_EMAIL;
import static pl.bk.pizza.store.domain.exception.ErrorCode.INVALID_EMAIL;
import static pl.bk.pizza.store.domain.exception.Preconditions.check;

@Component
public class EmailValidator
{
    public static void validateEmail(String email)
    {
        org.apache.commons.validator.routines.EmailValidator validator = org.apache.commons.validator.routines.EmailValidator.getInstance();
        check((isBlank(email)), () -> new InvalidEntityException(("Email is null or empty."), EMPTY_USER_EMAIL));
        check((!validator.isValid(email)), () -> new InvalidEntityException(("Invalid email address."), INVALID_EMAIL));
    }
}
