package pl.bk.pizza.store.domain.order;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static java.util.Collections.emptySet;
import static pl.bk.pizza.store.domain.service.IdGenerator.generateID;
import static pl.bk.pizza.store.domain.validator.EmailValidator.validateEmail;

@Service
public class OrderFactory
{
    public Order create(String email)
    {
        validateEmail(email);
        
        return new Order(
            generateID(),
            email,
            emptySet(),
            emptySet(),
            OrderStatus.STARTED,
            BigDecimal.ZERO
        );
    }
    
    public Order create()
    {
        return new Order(
            generateID(),
            null,
            emptySet(),
            emptySet(),
            OrderStatus.STARTED,
            BigDecimal.ZERO
        );
    }
}
