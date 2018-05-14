package pl.bk.pizza.store.domain.validator.product;

import pl.bk.pizza.store.domain.exception.InvalidEntityException;

import static org.apache.commons.lang.StringUtils.isBlank;
import static pl.bk.pizza.store.domain.exception.ErrorCode.EMPTY_KEBAB_DESCRIPTION;
import static pl.bk.pizza.store.domain.exception.ErrorCode.EMPTY_KEBAB_NAME;
import static pl.bk.pizza.store.domain.exception.Preconditions.check;

public class KebabValidator
{
    public static void validateDescription(String description)
    {
        check(isBlank(description), () -> new InvalidEntityException(
            "Description is null or empty.",
            EMPTY_KEBAB_DESCRIPTION
        ));
    }
    
    public static void validateName(String name)
    {
        check(isBlank(name), () -> new InvalidEntityException(
            "Name is null or empty.",
            EMPTY_KEBAB_NAME
        ));
    }
}
