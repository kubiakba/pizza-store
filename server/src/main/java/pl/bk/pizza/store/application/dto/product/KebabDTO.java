package pl.bk.pizza.store.application.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class KebabDTO implements ProductInfoDTO
{
    private String description;
    private String name;
}
