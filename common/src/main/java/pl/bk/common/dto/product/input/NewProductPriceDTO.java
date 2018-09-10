package pl.bk.common.dto.product.input;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class NewProductPriceDTO
{
    private BigDecimal price;
}
