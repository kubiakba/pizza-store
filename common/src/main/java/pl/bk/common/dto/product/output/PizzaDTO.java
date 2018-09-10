package pl.bk.common.dto.product.output;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.bk.common.dto.product.DoughDTO;
import pl.bk.common.dto.product.PizzaSizeDTO;
import pl.bk.common.dto.product.ProductStatusDTO;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class PizzaDTO extends ProductDTO
{
    private PizzaSizeDTO size;
    private DoughDTO dough;
    
    public PizzaDTO(String id, BigDecimal price, ProductStatusDTO productStatus, PizzaSizeDTO size, DoughDTO dough)
    {
        super(id, price, productStatus);
        this.size = size;
        this.dough = dough;
    }
}
