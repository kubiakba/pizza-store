package pl.bk.pizza.store.application.mapper.customer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.bk.common.dto.user.NewUserDTO;
import pl.bk.pizza.store.application.mapper.DtoToObjectMapper;
import pl.bk.pizza.store.domain.customer.user.User;
import pl.bk.pizza.store.domain.customer.user.UserFactory;

@AllArgsConstructor
@Component
public class NewUserMapper implements DtoToObjectMapper<NewUserDTO, User>
{
    private final UserFactory factory;
    
    @Override
    public User mapFromDTO(NewUserDTO newUserDto)
    {
        return factory.create(
            newUserDto.getEmail(),
            newUserDto.getPassword()
                             );
    }
}
