package pl.bk.pizza.store.domain.validator.order;

import pl.bk.pizza.store.domain.exception.MissingEntityException;
import pl.bk.pizza.store.domain.order.Order;
import reactor.core.publisher.Mono;

import static pl.bk.pizza.store.domain.exception.ErrorCode.MISSING_ORDER;

public class OrderValidator
{
    public static Mono<Order> orderShouldExists(String id)
    {
        return Mono.error(new MissingEntityException(
            "Cannot find order with id: " + id,
            MISSING_ORDER
        ));
    }
}
