package pl.bk.pizza.store.domain.validator.product;

import pl.bk.pizza.store.domain.exception.InvalidEntityException;

import static org.apache.commons.lang.StringUtils.isBlank;
import static pl.bk.pizza.store.domain.exception.ErrorCode.EMPTY_PIZZA_TOPPING_NAME;
import static pl.bk.pizza.store.domain.exception.Preconditions.check;

public class PizzaToppingValidator
{
    public static void validateName(String name)
    {
        check(isBlank(name), () -> new InvalidEntityException("PizzaTopping name is null or empty.", EMPTY_PIZZA_TOPPING_NAME));
    }
}
