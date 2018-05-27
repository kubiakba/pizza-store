package pl.bk.pizza.store.domain.validator.order;

import pl.bk.pizza.store.domain.exception.MissingEntityException;
import pl.bk.pizza.store.domain.order.Order;

import static pl.bk.pizza.store.domain.exception.ErrorCode.MISSING_ORDER;
import static pl.bk.pizza.store.domain.exception.Preconditions.check;

public class OrderValidator
{
    public static void orderShouldExists(Order order, String id)
    {
        check(order == null, () -> new MissingEntityException(
            "Cannot find order with id: " + id,
            MISSING_ORDER
        ));
    }
}
