package pl.bk.pizza.store.domain.discount.rule;

import com.google.common.collect.ImmutableSortedMap;
import lombok.AllArgsConstructor;
import pl.bk.pizza.store.domain.discount.Discount;
import pl.bk.pizza.store.domain.order.Order;

@AllArgsConstructor
public class OnlyOneDiscountRule implements DiscountRule
{
    private final Class<? extends Discount> discount;
    
    @Override
    public ImmutableSortedMap<Integer, Class<? extends Discount>> apply(Order order)
    {
        return ImmutableSortedMap.<Integer, Class<? extends Discount>>naturalOrder()
            .put(1, discount)
            .build();
    }
}
