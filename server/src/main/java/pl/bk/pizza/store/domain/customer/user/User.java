package pl.bk.pizza.store.domain.customer.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import pl.bk.pizza.store.domain.customer.address.Address;
import pl.bk.pizza.store.domain.customer.telephone.Telephone;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class User
{
    @Id
    private final String email;
    private String password;
    private String name;
    private String surname;
    private Address address;
    private Telephone telephone;
    private UserStatus status;
    private Points points;
    private String role;
    
    public void deactivateUser()
    {
        status = UserStatus.INACTIVE;
    }
    
    public void changeSurname(String surname)
    {
        this.surname = surname;
    }
    
    public void changeName(String name)
    {
        this.name = name;
    }
    
    public void addPoints(Integer numberOfPoints)
    {
        points = new Points(points.getAmount() + numberOfPoints);
    }
    
    public void changeTelephone(Telephone telephone)
    {
        this.telephone = telephone;
    }
}
