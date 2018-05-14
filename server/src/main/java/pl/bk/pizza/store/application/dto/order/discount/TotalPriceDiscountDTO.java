package pl.bk.pizza.store.application.dto.order.discount;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class TotalPriceDiscountDTO implements DiscountDTO
{
    private BigDecimal moneyLimitActivator;
    private BigDecimal moneyToReturn;
}
