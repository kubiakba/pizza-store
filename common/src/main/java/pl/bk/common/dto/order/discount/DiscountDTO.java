package pl.bk.common.dto.order.discount;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    property = "type")
@JsonSubTypes({
                  @JsonSubTypes.Type(value = TotalPriceDiscountDTO.class, name = "TotalPriceDiscount"),
                  @JsonSubTypes.Type(value = ExtraProductDiscountDTO.class, name = "ExtraProductDiscount"),
                  @JsonSubTypes.Type(value = PercentDiscountDTO.class, name = "PercentDiscount")
              })
@NoArgsConstructor
public abstract class DiscountDTO
{
}
