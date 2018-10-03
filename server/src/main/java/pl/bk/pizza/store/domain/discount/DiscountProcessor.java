package pl.bk.pizza.store.domain.discount;

import org.springframework.stereotype.Component;
import pl.bk.pizza.store.domain.order.Order;

import static java.util.stream.Stream.of;
import static pl.bk.pizza.store.domain.discount.DiscountPriority.prioritizeDiscounts;

@Component
public class DiscountProcessor
{
    public Order applyDiscounts(Order order)
    {
        return of(order)
            .peek(o -> prioritizeDiscounts.forEach((key, value) -> order.getDiscounts()
                                                                        .stream()
                                                                        .filter(discount -> discount.getClass().equals(value))
                                                                        .forEach(discount -> discount.apply(order)))).findFirst().get();
    }
}
