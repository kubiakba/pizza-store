package pl.bk.pizza.store.application.dto.product.output;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.bk.pizza.store.domain.product.ProductStatus;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
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
    private ProductStatus productStatus;
    
    ProductDTO(String id, BigDecimal price, ProductStatus productStatus)
    {
        this.id = id;
        this.price = price;
        this.productStatus = productStatus;
    }
}
