package pl.bk.pizza.store.application.dto.product.output;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.bk.pizza.store.domain.product.ProductStatus;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class KebabDTO extends ProductDTO
{
    private String name;
    private String description;
    
    public KebabDTO(String id, BigDecimal price, ProductStatus productStatus, String name, String description)
    {
        super(id, price, productStatus);
        this.name = name;
        this.description = description;
    }
}
