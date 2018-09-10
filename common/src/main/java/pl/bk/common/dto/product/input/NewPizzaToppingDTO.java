package pl.bk.common.dto.product.input;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class NewPizzaToppingDTO extends NewProductDTO
{
    private String toppingName;
    
    public NewPizzaToppingDTO(BigDecimal price, String toppingName)
    {
        super(price);
        this.toppingName = toppingName;
    }
}
