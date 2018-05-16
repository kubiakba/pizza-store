package pl.bk.pizza.store.domain.validator;

import pl.bk.pizza.store.domain.exception.InvalidEntityException;

import static org.apache.commons.lang.StringUtils.isBlank;
import static pl.bk.pizza.store.domain.exception.ErrorCode.EMPTY_USER_EMAIL;
import static pl.bk.pizza.store.domain.exception.ErrorCode.INVALID_EMAIL;
import static pl.bk.pizza.store.domain.exception.Preconditions.check;

public class EmailValidator
{
    public static void validateEmail(String email)
    {
        org.apache.commons.validator.routines.EmailValidator validator = org.apache.commons.validator.routines.EmailValidator.getInstance();
        
        check(isBlank(email), () -> new InvalidEntityException(
            "Email is null or empty.",
            EMPTY_USER_EMAIL
        ));
        
        check(!validator.isValid(email), () -> new InvalidEntityException(
            "Invalid format of email address: "+ email,
            INVALID_EMAIL
        ));
        
    }
}
