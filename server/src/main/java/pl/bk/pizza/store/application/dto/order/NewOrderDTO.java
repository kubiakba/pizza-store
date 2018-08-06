package pl.bk.pizza.store.application.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.bk.pizza.store.domain.customer.Address;
import pl.bk.pizza.store.domain.customer.Telephone;
import pl.bk.pizza.store.domain.order.DeliveryInfo;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NewOrderDTO
{
    private String email;
    private DeliveryInfoDTO deliveryInfoDTO;
}
