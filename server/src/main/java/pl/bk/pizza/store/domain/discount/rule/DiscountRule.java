package pl.bk.pizza.store.domain.discount.rule;

import pl.bk.pizza.store.domain.discount.Discount;
import pl.bk.pizza.store.domain.order.Order;

import java.util.Set;

public interface DiscountRule
{
    Set<Class<? extends Discount>> apply(Order order);
}
