package pl.bk.pizza.store.domain.discount;

import com.google.common.collect.ImmutableSortedMap;
import pl.bk.pizza.store.domain.discount.bonus.ExtraProductDiscount;
import pl.bk.pizza.store.domain.discount.bonus.PercentDiscount;
import pl.bk.pizza.store.domain.discount.bonus.TotalPriceDiscount;

public class DiscountPriority
{
    public static ImmutableSortedMap<Integer, Class<? extends Discount>> prioritizeDiscounts =
        ImmutableSortedMap.<Integer, Class<? extends Discount>>naturalOrder()
            .put(1, TotalPriceDiscount.class)
            .put(2, PercentDiscount.class)
            .put(3, ExtraProductDiscount.class)
            .build();
}
