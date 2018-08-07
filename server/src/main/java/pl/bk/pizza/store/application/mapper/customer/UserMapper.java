package pl.bk.pizza.store.application.mapper.customer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.bk.pizza.store.application.dto.user.UserDTO;
import pl.bk.pizza.store.application.mapper.ObjectToDtoMapper;
import pl.bk.pizza.store.domain.customer.user.User;

@AllArgsConstructor
@Component
public class UserMapper implements ObjectToDtoMapper<User, UserDTO>
{
    
    @Override
    public UserDTO mapToDTO(User user)
    {
        return new UserDTO(
            user.getEmail(),
            user.getStatus(),
            user.getPoints()
        );
    }
}
