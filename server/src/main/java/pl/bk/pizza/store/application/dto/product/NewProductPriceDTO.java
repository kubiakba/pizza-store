package pl.bk.pizza.store.application.dto.product;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class NewProductPriceDTO {

    private BigDecimal price;
}
