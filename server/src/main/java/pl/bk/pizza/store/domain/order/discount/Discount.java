package pl.bk.pizza.store.domain.order.discount;

import java.math.BigDecimal;

public interface Discount
{
    BigDecimal calculateDiscount(BigDecimal price);
}
