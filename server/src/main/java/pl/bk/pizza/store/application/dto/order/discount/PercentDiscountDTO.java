package pl.bk.pizza.store.application.dto.order.discount;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PercentDiscountDTO implements DiscountDTO
{
    private Integer discountPercent;
}
