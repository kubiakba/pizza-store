package pl.bk.pizza.store.application.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DeliveryInfoDTO
{
    private String name;
    private String surname;
    private AddressDTO addressDTO;
    private TelephoneDTO telephoneDTO;
}
