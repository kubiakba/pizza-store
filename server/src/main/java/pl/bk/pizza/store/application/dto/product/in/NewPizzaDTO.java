package pl.bk.pizza.store.application.dto.product.in;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.bk.pizza.store.domain.product.pizza.Dough;
import pl.bk.pizza.store.domain.product.pizza.PizzaSize;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class NewPizzaDTO extends NewProductDTO
{
    private PizzaSize size;
    private Dough dough;
    
    public NewPizzaDTO(BigDecimal price, PizzaSize size, Dough dough)
    {
        super(price);
        this.size = size;
        this.dough = dough;
    }
}
