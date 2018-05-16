package pl.bk.pizza.store.domain.product.pizza;

import lombok.Getter;
import pl.bk.pizza.store.domain.product.BaseProductInfo;
import pl.bk.pizza.store.domain.validator.product.PizzaValidator;

import java.math.BigDecimal;

@Getter
public class Pizza extends BaseProductInfo
{
    private final PizzaSize size;
    private final Dough dough;
    
    public Pizza(PizzaSize size, Dough dough, BigDecimal price)
    {
        super(price);
        PizzaValidator.validateSize(size);
        PizzaValidator.validateDough(dough);
        this.size = size;
        this.dough = dough;
    }
}
