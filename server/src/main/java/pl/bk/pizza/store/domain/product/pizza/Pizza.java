package pl.bk.pizza.store.domain.product.pizza;

import lombok.Getter;
import pl.bk.pizza.store.domain.product.BaseProductInfo;

import java.math.BigDecimal;

import static pl.bk.pizza.store.domain.validator.product.PizzaValidator.validateDough;
import static pl.bk.pizza.store.domain.validator.product.PizzaValidator.validateSize;

@Getter
public class Pizza extends BaseProductInfo
{
    private final PizzaSize size;
    private final Dough dough;
    
    public Pizza(PizzaSize size, Dough dough, BigDecimal price)
    {
        super(price);
        validateSize(size);
        validateDough(dough);
        this.size = size;
        this.dough = dough;
    }
}
