package pl.bk.pizza.store.domain.discount.rule;

import com.google.common.collect.ImmutableSortedMap;
import pl.bk.pizza.store.domain.discount.Discount;
import pl.bk.pizza.store.domain.order.Order;

public class DiscountRuleProcessor
{
    public ImmutableSortedMap<Integer, Class<? extends Discount>> process(DiscountRule rule, Order order)
    {
        return rule.apply(order);
    }
}
