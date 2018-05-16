package pl.bk.pizza.store.domain.product.kebab;

import lombok.Getter;
import pl.bk.pizza.store.domain.product.BaseProductInfo;

import java.math.BigDecimal;

import static pl.bk.pizza.store.domain.validator.product.KebabValidator.validateDescription;
import static pl.bk.pizza.store.domain.validator.product.KebabValidator.validateName;

@Getter
public class Kebab extends BaseProductInfo
{
    private final String name;
    private final String description;
    
    public Kebab(String description, String name, BigDecimal price)
    {
        super(price);
        validateDescription(description);
        validateName(name);
        this.name = name;
        this.description = description;
    }
}
