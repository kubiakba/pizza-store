package pl.bk.pizza.store.domain.discount.bonus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.bk.pizza.store.domain.discount.Discount;
import pl.bk.pizza.store.domain.order.Order;

import java.math.BigDecimal;

import static java.util.stream.Stream.of;

@AllArgsConstructor
@Getter
public class TotalPriceDiscount implements Discount
{
    private final BigDecimal minAmountOfPaidMoneyToActivateDiscount;
    private final BigDecimal moneyToReturn;
    
    public Order apply(Order order)
    {
        return of(order)
            .map(o -> o.getTotalPrice().compareTo(minAmountOfPaidMoneyToActivateDiscount) > 0 ?
                      order.setPriceAfterDiscount(order.getTotalPrice().subtract(moneyToReturn)) : o)
            .findFirst()
            .get();
    }
}
