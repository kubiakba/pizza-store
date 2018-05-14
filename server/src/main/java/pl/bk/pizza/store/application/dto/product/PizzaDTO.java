package pl.bk.pizza.store.application.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.bk.pizza.store.domain.product.pizza.Dough;
import pl.bk.pizza.store.domain.product.pizza.PizzaSize;

@AllArgsConstructor
@Getter
public class PizzaDTO implements ProductInfoDTO
{
    private PizzaSize size;
    private Dough dough;
}
