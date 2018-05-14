package pl.bk.pizza.store.domain.order;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bk.pizza.store.domain.validator.EmailValidator;

import java.math.BigDecimal;

import static java.util.Collections.emptySet;
import static pl.bk.pizza.store.domain.service.IdGenerator.generateID;

@Service
@AllArgsConstructor
public class OrderFactory
{
    private EmailValidator emailValidator;
    
    public Order create(String email)
    {
        emailValidator.validateEmail(email);
        
        return new Order(
            generateID(),
            email,
            emptySet(),
            emptySet(),
            OrderStatus.STARTED,
            BigDecimal.ZERO
        );
    }
}
