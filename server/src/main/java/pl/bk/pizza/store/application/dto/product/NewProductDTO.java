package pl.bk.pizza.store.application.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class NewProductDTO<T extends ProductInfoDTO>
{
    private BigDecimal price;
    private T productInfo;
}
