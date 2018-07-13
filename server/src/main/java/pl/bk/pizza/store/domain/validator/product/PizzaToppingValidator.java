package pl.bk.pizza.store.domain.validator.product;

import lombok.NoArgsConstructor;
import pl.bk.pizza.store.domain.exception.InvalidEntityException;

import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.lang.StringUtils.isBlank;
import static pl.bk.pizza.store.domain.exception.ErrorCode.EMPTY_PIZZA_TOPPING_NAME;
import static pl.bk.pizza.store.domain.exception.Preconditions.check;

@NoArgsConstructor(access = PRIVATE)
public class PizzaToppingValidator
{
    public static void validateName(String name)
    {
        check(isBlank(name), () -> new InvalidEntityException(
            "PizzaTopping name is null or empty.",
            EMPTY_PIZZA_TOPPING_NAME
        ));
    }
}
