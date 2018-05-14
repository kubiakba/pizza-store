package pl.bk.pizza.store.domain.product.pizza;

import lombok.Getter;
import pl.bk.pizza.store.domain.product.ProductInfo;
import pl.bk.pizza.store.domain.validator.product.PizzaValidator;

@Getter
public class Pizza implements ProductInfo
{
    private final PizzaSize size;
    private final Dough dough;
    
    public Pizza(PizzaSize size, Dough dough)
    {
        PizzaValidator.validateSize(size);
        PizzaValidator.validateDough(dough);
        this.size = size;
        this.dough = dough;
    }
}
