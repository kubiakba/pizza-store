package pl.bk.pizza.store.domain.customer.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import pl.bk.pizza.store.domain.customer.Address;
import pl.bk.pizza.store.domain.customer.Telephone;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class User
{
    @Id
    private final String email;
    private String name;
    private String surname;
    private String password;
    private Address address;
    private Telephone telephone;
    private UserStatus status;
    private Integer points;
    private String role;
    
    public void deactivateUser()
    {
        status = UserStatus.INACTIVE;
    }
    
    public void addPoints(Integer numberOfPoints)
    {
        points +=numberOfPoints;
    }
}
