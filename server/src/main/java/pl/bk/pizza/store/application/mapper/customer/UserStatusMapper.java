package pl.bk.pizza.store.application.mapper.customer;

import org.springframework.stereotype.Component;
import pl.bk.common.dto.user.UserStatusDTO;
import pl.bk.pizza.store.application.mapper.GenericMapper;
import pl.bk.pizza.store.domain.customer.user.UserStatus;

@Component
public class UserStatusMapper implements GenericMapper<UserStatusDTO,UserStatus>
{
    @Override
    public UserStatus mapFromDTO(UserStatusDTO userStatusDTO)
    {
        return UserStatus.valueOf(userStatusDTO.name());
    }
    
    @Override
    public UserStatusDTO mapToDTO(UserStatus userStatus)
    {
        return UserStatusDTO.valueOf(userStatus.name());
    }
}
