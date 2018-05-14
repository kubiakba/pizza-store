package pl.bk.pizza.store.application.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PizzaToppingDTO implements ProductInfoDTO
{
    private String name;
}
