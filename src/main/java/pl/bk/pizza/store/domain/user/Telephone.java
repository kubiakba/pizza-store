package pl.bk.pizza.store.domain.user;

import pl.bk.pizza.store.domain.exception.InvalidEntityException;

import static org.apache.commons.lang.StringUtils.isBlank;
import static pl.bk.pizza.store.domain.exception.ErrorCode.*;
import static pl.bk.pizza.store.domain.exception.Preconditions.check;

public class Telephone {

    private final String number;

    public Telephone(String number){
        validateNumber(number);
        this.number = number;
    }

    private void validateNumber(String number) {
        check(isBlank(number), () -> new InvalidEntityException(("Number is null or empty."),
            EMPTY_NUMBER));
    }

    public String getNumber() {
        return number;
    }
}
