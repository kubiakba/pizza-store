package pl.bk.pizza.store.domain.discount.rule;

import lombok.AllArgsConstructor;
import pl.bk.pizza.store.domain.discount.Discount;
import pl.bk.pizza.store.domain.order.Order;

import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

@AllArgsConstructor
public class OnlyOneDiscountRule implements DiscountRule
{
    private final Class<? extends Discount> discount;
    
    @Override
    public Set<Class<? extends Discount>> apply(Order order)
    {
        return newHashSet(discount);
    }
}
