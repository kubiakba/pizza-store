package pl.bk.common.dto.order;

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
