package pl.bk.pizza.store.application.dto.product.in;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class NewProductPriceDTO
{
    private BigDecimal price;
}
