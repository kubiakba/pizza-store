package pl.bk.pizza.store.domain.discount.rule;

import lombok.AllArgsConstructor;
import pl.bk.pizza.store.domain.discount.Discount;
import pl.bk.pizza.store.domain.order.Order;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static java.time.Instant.ofEpochMilli;
import static java.time.LocalDateTime.ofInstant;
import static java.time.ZoneId.of;

@AllArgsConstructor
public class BeforeHourRule implements DiscountRule
{
    private final int upperHour;
    private final Set<Class<? extends Discount>> discountsToApply;
    
    @Override
    public Set<Class<? extends Discount>> apply(Order order)
    {
        return Stream.of(ofInstant(ofEpochMilli(order.getOrderDateTime()), of("Europe/Warsaw")))
                     .map(time -> time.getHour() < upperHour ? discountsToApply : new HashSet<Class<? extends Discount>>())
                     .findFirst()
                     .get();
    }
}
