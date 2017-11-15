package pl.bk.pizza.store.domain.product;

import pl.bk.pizza.store.domain.exception.InvalidEntityException;

import static pl.bk.pizza.store.domain.exception.ErrorCode.*;
import static pl.bk.pizza.store.domain.exception.Preconditions.check;

public class Pizza implements ProductInfo{

    private final PizzaSize size;
    private final Dough dough;

    public Pizza(PizzaSize size, Dough dough) {
        validateSize(size);
        validateDough(dough);
        this.size = size;
        this.dough = dough;
    }

    private void validateSize(PizzaSize size) {
        check(size == null,() -> new InvalidEntityException("PizzaSize is null.", EMPTY_PIZZA_SIZE));
    }

    private void validateDough(Dough dough) {
        check(dough == null,() -> new InvalidEntityException("Dough is null.", EMPTY_DOUGH));
    }

    public PizzaSize getSize() {
        return size;
    }

    public Dough getDough() {
        return dough;
    }

}
