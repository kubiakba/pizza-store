package pl.bk.pizza.store.application.mapper.customer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.bk.pizza.store.application.dto.user.NewUserDTO;
import pl.bk.pizza.store.application.mapper.DtoToObjectMapper;
import pl.bk.pizza.store.domain.customer.address.Address;
import pl.bk.pizza.store.domain.customer.telephone.Telephone;
import pl.bk.pizza.store.domain.customer.user.User;
import pl.bk.pizza.store.domain.customer.user.UserFactory;

@AllArgsConstructor
@Component
public class NewUserMapper implements DtoToObjectMapper<NewUserDTO, User>
{
    private final UserFactory factory;
    private final AddressMapper addressMapper;
    private final TelephoneMapper telephoneMapper;
    
    @Override
    public User mapFromDTO(NewUserDTO newUserDTO)
    {
        final Address address = addressMapper.mapFromDTO(newUserDTO.getAddress());
        final Telephone telephone = telephoneMapper.mapFromDTO(newUserDTO.getTelephone());
        
        return factory.create(
            newUserDTO.getEmail(),
            newUserDTO.getName(),
            newUserDTO.getSurname(),
            newUserDTO.getPassword(),
            address,
            telephone,
            "USER"
                             );
    }
}
