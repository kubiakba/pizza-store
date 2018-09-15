package pl.bk.pizza.store.domain.discount.bonus;

import lombok.AllArgsConstructor;
import pl.bk.pizza.store.domain.discount.Discount;
import pl.bk.pizza.store.domain.order.Order;

import java.math.BigDecimal;

import static java.util.stream.Stream.of;

@AllArgsConstructor
public class PercentDiscount implements Discount
{
    private final BigDecimal discountPercent;
    private final String productId;
    
    @Override
    public Order apply(Order order)
    {
        return of(order).map(o -> o.getProducts()
                                   .stream()
                                   .anyMatch(product -> product.getId().equals(productId)) ? applyDiscount(o) : order)
                        .findFirst()
                        .get();
    }
    
    private Order applyDiscount(Order order)
    {
        final BigDecimal price = getProductPrice(order);
        final long numberOfProducts = getNumberOfProducts(order);
        return order.setPriceAfterDiscount(order.getTotalPriceWithDiscounts()
                                                .subtract(calculatePriceToWithdraw(price, numberOfProducts)));
    }
    
    private BigDecimal getProductPrice(Order order)
    {
        return order.getProducts()
                    .stream()
                    .filter(product -> product.getId().equals(productId))
                    .findFirst()
                    .get()
                    .getPrice();
    }
    
    private long getNumberOfProducts(Order order)
    {
        return order.getProducts()
                    .stream()
                    .filter(product -> product.getId().equals(productId))
                    .count();
    }
    
    private BigDecimal calculatePriceToWithdraw(BigDecimal price, long numberOfProducts)
    {
        return new BigDecimal(numberOfProducts)
            .multiply(price.multiply(discountPercent));
    }
}
