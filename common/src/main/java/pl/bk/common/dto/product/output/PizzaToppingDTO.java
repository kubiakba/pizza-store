package pl.bk.common.dto.product.output;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.bk.common.dto.product.ProductStatusDTO;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class PizzaToppingDTO extends ProductDTO
{
    private String name;
    
    public PizzaToppingDTO(String id, BigDecimal price, ProductStatusDTO productStatus, String name)
    {
        super(id, price, productStatus);
        this.name = name;
    }
}
