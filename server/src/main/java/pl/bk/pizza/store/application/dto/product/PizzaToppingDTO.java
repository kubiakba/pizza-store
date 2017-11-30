package pl.bk.pizza.store.application.dto.product;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PizzaToppingDTO extends ProductInfoDTO {

    private String name;
}
