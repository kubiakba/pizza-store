package pl.bk.common.dto.product.input;

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
