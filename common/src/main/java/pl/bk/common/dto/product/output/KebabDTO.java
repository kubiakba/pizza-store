package pl.bk.common.dto.product.output;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.bk.common.dto.product.ProductStatusDTO;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class KebabDTO extends ProductDTO
{
    private String name;
    private String description;
    
    public KebabDTO(String id, BigDecimal price, ProductStatusDTO productStatus, String name, String description)
    {
        super(id, price, productStatus);
        this.name = name;
        this.description = description;
    }
}
