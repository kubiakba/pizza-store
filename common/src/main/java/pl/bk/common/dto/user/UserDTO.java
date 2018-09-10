package pl.bk.common.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserDTO
{
    private String email;
    private UserStatusDTO status;
    private int points;
}
