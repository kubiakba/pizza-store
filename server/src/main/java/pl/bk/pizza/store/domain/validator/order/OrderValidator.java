package pl.bk.pizza.store.domain.validator.order;

import pl.bk.pizza.store.domain.exception.AppException;
import pl.bk.pizza.store.domain.exception.MissingEntityException;
import pl.bk.pizza.store.domain.order.Order;
import pl.bk.pizza.store.domain.order.OrderStatus;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static pl.bk.pizza.store.domain.exception.ErrorCode.INVALID_ORDER_STATUS;
import static pl.bk.pizza.store.domain.exception.ErrorCode.MISSING_ORDER;
import static pl.bk.pizza.store.domain.exception.Preconditions.check;
import static pl.bk.pizza.store.domain.order.OrderStatus.*;

public class OrderValidator
{
    public static Mono<Order> orderShouldExists(String id)
    {
        return Mono.error(new MissingEntityException(
            "Cannot find order with id: " + id,
            MISSING_ORDER
        ));
    }
    
    public static void realizationShouldBeStarted(OrderStatus status)
    {
        check(!TO_REALIZATION.equals(status), () -> new AppException(
            "Order status should be to-realization",
            INTERNAL_SERVER_ERROR,
            INVALID_ORDER_STATUS
        ));
    }
}
