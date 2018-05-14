package pl.bk.pizza.store.domain.order.discount;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class TotalPriceDiscount implements Discount
{
    private final BigDecimal moneyLimitActivator;
    private final BigDecimal moneyToReturn;
    
    @Override
    public BigDecimal calculateDiscount(BigDecimal moneySpend)
    {
        return wasEnoughMoneyPaid(moneySpend) ? moneySpend.subtract(moneyToReturn) : moneySpend;
    }
    
    private boolean wasEnoughMoneyPaid(BigDecimal moneySpend)
    {
        return moneyLimitActivator.compareTo(moneySpend) > 0;
    }
}
