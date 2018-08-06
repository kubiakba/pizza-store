package pl.bk.pizza.store.application.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.bk.pizza.store.application.dto.order.AddressDTO;
import pl.bk.pizza.store.application.dto.order.TelephoneDTO;
import pl.bk.pizza.store.domain.customer.user.UserStatus;

@AllArgsConstructor
@Getter
public class UserDTO
{
    private String email;
    private UserStatus status;
    private int points;
}
