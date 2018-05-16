package pl.bk.pizza.store.application.dto.order.discount;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type")
@JsonSubTypes({
                  @JsonSubTypes.Type(value = PercentDiscountDTO.class, name = "PercentDiscount"),
                  @JsonSubTypes.Type(value = TotalPriceDiscountDTO.class, name = "TotalPriceDiscount")
              })
public interface DiscountDTO
{
}
