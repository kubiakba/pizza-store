package pl.bk.pizza.store.application.dto.product.output;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.bk.pizza.store.domain.product.ProductStatus;
import pl.bk.pizza.store.domain.product.pizza.Dough;
import pl.bk.pizza.store.domain.product.pizza.PizzaSize;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class PizzaDTO extends ProductDTO
{
    private PizzaSize size;
    private Dough dough;
    
    public PizzaDTO(String id, BigDecimal price, ProductStatus productStatus, PizzaSize size, Dough dough)
    {
        super(id, price, productStatus);
        this.size = size;
        this.dough = dough;
    }
}
