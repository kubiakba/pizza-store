package pl.bk.pizza.store.application.mapper.customer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.bk.pizza.store.application.dto.user.AddressDTO;
import pl.bk.pizza.store.application.dto.user.TelephoneDTO;
import pl.bk.pizza.store.application.dto.user.UserDTO;
import pl.bk.pizza.store.application.mapper.ObjectToDtoMapper;
import pl.bk.pizza.store.domain.customer.user.User;

@AllArgsConstructor
@Component
public class UserMapper implements ObjectToDtoMapper<User, UserDTO>
{
    private final AddressMapper addressMapper;
    private final TelephoneMapper telephoneMapper;
    
    @Override
    public UserDTO mapToDTO(User user)
    {
        final AddressDTO addressDTO = addressMapper.mapToDTO(user.getAddress());
        final TelephoneDTO telephoneDTO = telephoneMapper.mapToDTO(user.getTelephone());
        return new UserDTO(user.getEmail(), user.getName(), user.getSurname(), addressDTO, telephoneDTO, 0);
    }
}
