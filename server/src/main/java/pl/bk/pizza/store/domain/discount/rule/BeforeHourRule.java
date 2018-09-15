package pl.bk.pizza.store.domain.discount.rule;

import com.google.common.collect.ImmutableSortedMap;
import lombok.AllArgsConstructor;
import pl.bk.pizza.store.domain.discount.Discount;
import pl.bk.pizza.store.domain.discount.DiscountPriority;
import pl.bk.pizza.store.domain.order.Order;

import java.util.List;
import java.util.stream.Stream;

import static java.time.Instant.ofEpochMilli;
import static java.time.LocalDateTime.ofInstant;
import static java.time.ZoneId.of;
import static java.util.stream.IntStream.range;

@AllArgsConstructor
public class BeforeHourRule implements DiscountRule
{
    private final int upperHour;
    private final List<Class<? extends Discount>> discountsToApply;
    
    @Override
    public ImmutableSortedMap<Integer, Class<? extends Discount>> apply(Order order)
    {
        return Stream.of(ofInstant(ofEpochMilli(order.getOrderDateTime()), of("Europe/Warsaw")))
                     .map(time -> time.getHour() < upperHour ? applyDiscounts() : DiscountPriority.discounts)
                     .findFirst()
                     .get();
    }
    
    private ImmutableSortedMap<Integer, Class<? extends Discount>> applyDiscounts()
    {
        final ImmutableSortedMap.Builder<Integer, Class<? extends Discount>> discountsMapBuilder = ImmutableSortedMap.naturalOrder();
        range(1, discountsToApply.size() + 1)
            .forEach(index -> discountsMapBuilder.put(index, discountsToApply.get(index - 1)));
        return discountsMapBuilder.build();
    }
}
