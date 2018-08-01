package pl.bk.pizza.store.application.mapper.customer;

import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import pl.bk.pizza.store.application.dto.user.NewNotRegisteredUserDTO;
import pl.bk.pizza.store.application.mapper.DtoToObjectMapper;
import pl.bk.pizza.store.domain.customer.Address;
import pl.bk.pizza.store.domain.customer.Telephone;
import pl.bk.pizza.store.domain.customer.user.User;
import pl.bk.pizza.store.domain.customer.user.UserFactory;

import static org.apache.commons.lang.StringUtils.*;

@AllArgsConstructor
@Component
public class NewNotRegisteredUserMapper implements DtoToObjectMapper<NewNotRegisteredUserDTO, User>
{
    private final UserFactory factory;
    private final AddressMapper addressMapper;
    private final TelephoneMapper telephoneMapper;
    
    @Override
    public User mapFromDTO(NewNotRegisteredUserDTO newUserDto)
    {
        final Address address = addressMapper.mapFromDTO(newUserDto.getAddress());
        final Telephone telephone = telephoneMapper.mapFromDTO(newUserDto.getTelephone());
        
        return factory.create(
            newUserDto.getEmail(),
            newUserDto.getName(),
            newUserDto.getSurname(),
            EMPTY,
            address,
            telephone
                             );
        
    }
}
