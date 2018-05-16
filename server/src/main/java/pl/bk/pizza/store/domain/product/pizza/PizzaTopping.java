package pl.bk.pizza.store.domain.product.pizza;

import lombok.Getter;
import pl.bk.pizza.store.domain.product.BaseProductInfo;

import java.math.BigDecimal;

import static pl.bk.pizza.store.domain.validator.product.PizzaToppingValidator.validateName;

@Getter
public class PizzaTopping extends BaseProductInfo
{
    private final String name;
    
    public PizzaTopping(String name, BigDecimal price)
    {
        super(price);
        validateName(name);
        this.name = name;
    }
}
