package pl.bk.common.dto.order.discount;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ExtraProductDiscountDTO extends DiscountDTO
{
    private final Integer numberOfBoughtProducts;
    private final String productId;
    private final Integer extraProducts;
}
