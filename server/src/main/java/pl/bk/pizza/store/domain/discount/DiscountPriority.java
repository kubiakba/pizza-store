package pl.bk.pizza.store.domain.discount;

import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.Ordering;
import pl.bk.pizza.store.domain.discount.bonus.ExtraProductDiscount;
import pl.bk.pizza.store.domain.discount.bonus.PercentDiscount;
import pl.bk.pizza.store.domain.discount.bonus.TotalPriceDiscount;

import java.util.Set;

public class DiscountPriority
{
    public static ImmutableSortedMap<Integer, Class<? extends Discount>> prioritizeDiscounts =
        ImmutableSortedMap.<Integer, Class<? extends Discount>>naturalOrder()
            .put(1, TotalPriceDiscount.class)
            .put(2, PercentDiscount.class)
            .put(3, ExtraProductDiscount.class)
            .build();
    
    public static ImmutableSortedMap<Integer, Class<? extends Discount>> none =
        ImmutableSortedMap.<Integer, Class<? extends Discount>>naturalOrder()
            .build();
    
    public ImmutableSortedMap<Integer, Class<? extends Discount>> prioritizeDiscounts(Set<Class<? extends Discount>> discounts)
    {
        final ImmutableSortedMap.Builder<Integer, Class<? extends Discount>> builder =
            new ImmutableSortedMap.Builder<>(Ordering.natural());
        discounts.forEach(discount -> builder.put(getKey(discount), discount));
        return builder.build();
    }
    
    private Integer getKey(Class<? extends Discount> discount)
    {
        return prioritizeDiscounts
            .entrySet()
            .stream()
            .filter(prioritizeDiscount -> prioritizeDiscount.getValue().equals(discount))
            .findAny()
            .get()
            .getKey();
    }
    
}
