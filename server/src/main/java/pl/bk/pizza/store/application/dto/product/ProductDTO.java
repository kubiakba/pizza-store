package pl.bk.pizza.store.application.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.bk.pizza.store.domain.product.ProductStatus;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class ProductDTO<T extends ProductInfoDTO>
{
    private String id;
    private BigDecimal price;
    private ProductStatus productStatus;
    private T productInfo;
}
