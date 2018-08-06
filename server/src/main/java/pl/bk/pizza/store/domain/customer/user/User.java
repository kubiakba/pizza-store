package pl.bk.pizza.store.domain.customer.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.bk.pizza.store.domain.customer.Address;
import pl.bk.pizza.store.domain.customer.Telephone;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Document(collection = "user")
public class User
{
    @Id
    private final String email;
    private String password;
    private UserStatus status;
    private Integer points;
    private String role;
    
    public User deactivateUser()
    {
        status = UserStatus.INACTIVE;
        return this;
    }
    
    public User addPoints(Integer numberOfPoints)
    {
        points += numberOfPoints;
        return this;
    }
}
