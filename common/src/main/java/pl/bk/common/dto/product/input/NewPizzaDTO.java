package pl.bk.common.dto.product.input;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.bk.common.dto.product.DoughDTO;
import pl.bk.common.dto.product.PizzaSizeDTO;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class NewPizzaDTO extends NewProductDTO
{
    private PizzaSizeDTO size;
    private DoughDTO dough;
    
    public NewPizzaDTO(BigDecimal price, PizzaSizeDTO size, DoughDTO dough)
    {
        super(price);
        this.size = size;
        this.dough = dough;
    }
}
