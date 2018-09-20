package pl.bk.pizza.store.domain.discount;

import com.google.common.collect.ImmutableSortedMap;
import org.springframework.stereotype.Component;
import pl.bk.pizza.store.domain.order.Order;

import static pl.bk.pizza.store.domain.discount.DiscountPriority.prioritizeDiscounts;

//TODO add rules to processor

@Component
public class DiscountProcessor
{
    public Order applyDiscounts(Order order)
    {
        return apply(prioritizeDiscounts, order);
    }
    
    private Order apply(ImmutableSortedMap<Integer, Class<? extends Discount>> discounts, Order order)
    {
        discounts.forEach((key, value) -> order.getDiscounts()
                                               .stream()
                                               .filter(discount -> discount.getClass().equals(value))
                                               .forEach(discount -> discount.apply(order)));
        return order;
    }
}
