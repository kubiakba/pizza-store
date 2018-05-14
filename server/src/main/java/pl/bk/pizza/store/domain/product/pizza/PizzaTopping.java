package pl.bk.pizza.store.domain.product.pizza;

import lombok.Getter;
import pl.bk.pizza.store.domain.product.ProductInfo;

import static pl.bk.pizza.store.domain.validator.product.PizzaToppingValidator.validateName;

@Getter
public class PizzaTopping implements ProductInfo
{
    private final String name;
    
    public PizzaTopping(String name)
    {
        validateName(name);
        this.name = name;
    }
}
