package pl.bk.common.dto.order.discount;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class TotalPriceDiscountDTO extends DiscountDTO
{
    private final BigDecimal minAmountOfPaidMoneyToActivateDiscount;
    private final BigDecimal moneyToReturn;
}
