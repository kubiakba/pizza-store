package pl.bk.pizza.store.domain.discount;

import pl.bk.pizza.store.domain.order.Order;

public interface Discount
{
    Order apply(Order order);
}
