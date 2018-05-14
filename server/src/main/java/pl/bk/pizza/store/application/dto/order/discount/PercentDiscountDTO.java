package pl.bk.pizza.store.application.dto.order.discount;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PercentDiscountDTO implements DiscountDTO
{
    private Integer discountPercent;
}
