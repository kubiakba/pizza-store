package pl.bk.pizza.store.domain.validator.customer;

import pl.bk.pizza.store.domain.customer.Telephone;
import pl.bk.pizza.store.domain.exception.InvalidEntityException;

import static org.apache.commons.lang.StringUtils.isBlank;
import static pl.bk.pizza.store.domain.exception.ErrorCode.EMPTY_NUMBER;
import static pl.bk.pizza.store.domain.exception.ErrorCode.EMPTY_TELEPHONE_NUMBER;
import static pl.bk.pizza.store.domain.exception.Preconditions.check;

public class TelephoneValidator
{
    public static void validateTelephone(Telephone telephone)
    {
        check((telephone == null), () -> new InvalidEntityException("Telephone is null.", EMPTY_TELEPHONE_NUMBER));
    }
    
    public static void validateNumber(String number)
    {
        check(isBlank(number), () -> new InvalidEntityException(
            ("Number is null or empty."),
            EMPTY_NUMBER
        ));
    }
}
