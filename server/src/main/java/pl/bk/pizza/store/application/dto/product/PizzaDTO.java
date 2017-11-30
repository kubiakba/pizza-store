package pl.bk.pizza.store.application.dto.product;

import lombok.Data;
import lombok.EqualsAndHashCode;

import pl.bk.pizza.store.domain.product.Dough;
import pl.bk.pizza.store.domain.product.PizzaSize;

@EqualsAndHashCode(callSuper = true)
@Data
public class PizzaDTO extends ProductInfoDTO {

    private PizzaSize size;
    private Dough dough;
}
