package pl.bk.pizza.store.domain.order.discount.product;

import pl.bk.pizza.store.domain.order.discount.Discount;

import java.math.BigDecimal;

public class StandardDiscount implements Discount {

    private final BigDecimal discountPercent;

    public StandardDiscount(BigDecimal discountPercent) {
        this.discountPercent = discountPercent;
    }

    @Override
    public BigDecimal calculateDiscount(BigDecimal price) {
        return price.multiply(discountPercent);
    }

    public BigDecimal getDiscountPercent() {
        return discountPercent;
    }
}
