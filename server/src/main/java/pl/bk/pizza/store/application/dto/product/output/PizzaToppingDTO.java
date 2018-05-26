package pl.bk.pizza.store.application.dto.product.output;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.bk.pizza.store.domain.product.ProductStatus;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class PizzaToppingDTO extends ProductDTO
{
    private String name;
    
    public PizzaToppingDTO(String id, BigDecimal price, ProductStatus productStatus, String name)
    {
        super(id, price, productStatus);
        this.name = name;
    }
}
