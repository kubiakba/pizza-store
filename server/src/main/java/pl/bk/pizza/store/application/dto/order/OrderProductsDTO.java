package pl.bk.pizza.store.application.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderProductsDTO
{
    private String orderId;
    private String[] productsIds;
}
