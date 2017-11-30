package pl.bk.pizza.store.application.dto.order.discount;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class StandardDiscountDTO implements DiscountDTO {

    private BigDecimal discountPercent;
}
