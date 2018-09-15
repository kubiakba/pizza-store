package pl.bk.pizza.store.domain.discount.rule;

import com.google.common.collect.ImmutableSortedMap;
import pl.bk.pizza.store.domain.discount.Discount;
import pl.bk.pizza.store.domain.order.Order;

public interface DiscountRule
{
    ImmutableSortedMap<Integer, Class<? extends Discount>> apply(Order order);
}
