package pl.bk.pizza.store.domain.discount.bonus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.bk.pizza.store.domain.discount.Discount;
import pl.bk.pizza.store.domain.order.Order;

import static java.util.stream.IntStream.range;
import static java.util.stream.Stream.of;

@AllArgsConstructor
@Getter
public class ExtraProductDiscount implements Discount
{
    private final int numberOfBoughtProducts;
    private final String productId;
    private final Integer extraProducts;
    
    @Override
    public Order apply(Order order)
    {
        return of(order).map(o -> o.getProducts()
                                   .stream()
                                   .filter(p -> extraProducts > 0)
                                   .filter(p -> p.getId().equals(productId))
                                   .count() > numberOfBoughtProducts ? addExtraProducts(o) : o)
                        .findFirst()
                        .get();
    }
    
    private Order addExtraProducts(Order order)
    {
        return of(order)
            .flatMap(o -> range(0, extraProducts)
                .mapToObj(x -> o.addProduct(o.getProducts()
                                             .stream()
                                             .filter(p -> p.getId().equals(productId))
                                             .findFirst()
                                             .get())))
            .findFirst()
            .get();
    }
}
