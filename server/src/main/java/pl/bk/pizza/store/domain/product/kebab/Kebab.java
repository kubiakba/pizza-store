package pl.bk.pizza.store.domain.product.kebab;

import lombok.Getter;
import pl.bk.pizza.store.domain.product.ProductInfo;

import static pl.bk.pizza.store.domain.validator.product.KebabValidator.validateDescription;
import static pl.bk.pizza.store.domain.validator.product.KebabValidator.validateName;

@Getter
public class Kebab implements ProductInfo
{
    private final String name;
    private final String description;
    
    public Kebab(String description, String name)
    {
        validateDescription(description);
        validateName(name);
        this.name = name;
        this.description = description;
    }
}
