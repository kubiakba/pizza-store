package pl.bk.pizza.store.application.dto.order.discount;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TotalPriceDiscountDTO implements DiscountDTO{

    private BigDecimal minAmountOfPaidMoneyToActivateDiscount;
    private BigDecimal moneyToReturn;
}
