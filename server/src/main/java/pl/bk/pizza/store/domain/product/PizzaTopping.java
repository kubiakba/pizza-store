package pl.bk.pizza.store.domain.product;

import pl.bk.pizza.store.domain.exception.InvalidEntityException;

import static org.apache.commons.lang.StringUtils.isBlank;
import static pl.bk.pizza.store.domain.exception.ErrorCode.*;
import static pl.bk.pizza.store.domain.exception.Preconditions.check;

public class PizzaTopping implements ProductInfo{

    private final String name;

    public PizzaTopping(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        check(isBlank(name),() -> new InvalidEntityException("PizzaTopping name is null or empty.", EMPTY_PIZZA_TOPPING_NAME));
    }

    public String getName() {
        return name;
    }
}
