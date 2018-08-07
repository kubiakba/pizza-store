package pl.bk.pizza.store.application.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NewOrderDTO
{
    private String email;
    private DeliveryInfoDTO deliveryInfoDTO;
}
