package pl.bk.pizza.store.application.dto.product.in;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class NewKebabDTO extends NewProductDTO
{
    private String description;
    private String kebabName;
    
    public NewKebabDTO(BigDecimal price, String description, String kebabName)
    {
        super(price);
        this.description = description;
        this.kebabName = kebabName;
    }
}
