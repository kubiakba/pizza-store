package pl.bk.pizza.store.domain.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.bk.pizza.store.domain.customer.Address;
import pl.bk.pizza.store.domain.customer.Telephone;

@AllArgsConstructor
@Getter
public class DeliveryInfo
{
    private String name;
    private String surname;
    private Address address;
    private Telephone telephone;
}
