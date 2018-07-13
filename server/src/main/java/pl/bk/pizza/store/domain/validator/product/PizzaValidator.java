package pl.bk.pizza.store.domain.validator.product;

import lombok.NoArgsConstructor;
import pl.bk.pizza.store.domain.exception.InvalidEntityException;
import pl.bk.pizza.store.domain.product.pizza.Dough;
import pl.bk.pizza.store.domain.product.pizza.PizzaSize;

import static lombok.AccessLevel.PRIVATE;
import static pl.bk.pizza.store.domain.exception.ErrorCode.EMPTY_DOUGH;
import static pl.bk.pizza.store.domain.exception.ErrorCode.EMPTY_PIZZA_SIZE;
import static pl.bk.pizza.store.domain.exception.Preconditions.check;

@NoArgsConstructor(access = PRIVATE)
public class PizzaValidator
{
    public static void validateSize(PizzaSize size)
    {
        check(size == null, () -> new InvalidEntityException(
            "PizzaSize is null.",
            EMPTY_PIZZA_SIZE
        ));
    }
    
    public static void validateDough(Dough dough)
    {
        check(dough == null, () -> new InvalidEntityException(
            "Dough is null.",
            EMPTY_DOUGH
        ));
    }
}
