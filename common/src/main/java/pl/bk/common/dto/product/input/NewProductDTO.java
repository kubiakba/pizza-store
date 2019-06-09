package pl.bk.common.dto.product.input;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    property = "type")
@JsonSubTypes({
                  @JsonSubTypes.Type(value = NewPizzaToppingDTO.class, name = "PizzaTopping"),
                  @JsonSubTypes.Type(value = NewPizzaDTO.class, name = "Pizza"),
                  @JsonSubTypes.Type(value = NewKebabDTO.class, name = "Kebab")
              })
@NoArgsConstructor
public abstract class NewProductDTO
{
    private BigDecimal price;
    
    NewProductDTO(BigDecimal price)
    {
        this.price = price;
    }
}

