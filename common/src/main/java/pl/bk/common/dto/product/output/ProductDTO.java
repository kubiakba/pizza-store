package pl.bk.common.dto.product.output;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.bk.common.dto.product.ProductStatusDTO;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    property = "type")
@JsonSubTypes({
                  @JsonSubTypes.Type(value = PizzaToppingDTO.class, name = "PizzaTopping"),
                  @JsonSubTypes.Type(value = PizzaDTO.class, name = "Pizza"),
                  @JsonSubTypes.Type(value = KebabDTO.class, name = "Kebab")
              })
public abstract class ProductDTO
{
    private String id;
    private BigDecimal price;
    private ProductStatusDTO productStatus;
    
    ProductDTO(String id, BigDecimal price, ProductStatusDTO productStatus)
    {
        this.id = id;
        this.price = price;
        this.productStatus = productStatus;
    }
}
