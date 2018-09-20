package pl.bk.common.dto.order.discount;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class PercentDiscountDTO extends DiscountDTO
{
    private final BigDecimal discountPercent;
    private final String productId;
}
