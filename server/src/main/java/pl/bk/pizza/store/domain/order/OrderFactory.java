package pl.bk.pizza.store.domain.order;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static java.util.Collections.emptySet;
import static pl.bk.pizza.store.domain.service.IdGenerator.generateID;
import static pl.bk.pizza.store.domain.validator.EmailValidator.validateEmail;
import static pl.bk.pizza.store.domain.validator.customer.AddressValidator.validateAddress;
import static pl.bk.pizza.store.domain.validator.customer.TelephoneValidator.validateTelephone;
import static pl.bk.pizza.store.domain.validator.customer.UserValidator.validateName;
import static pl.bk.pizza.store.domain.validator.customer.UserValidator.validateSurname;

@Service
public class OrderFactory
{
    public Order create(String email, DeliveryInfo deliveryInfo)
    {
        validateEmail(email);
        validateName(deliveryInfo.getName());
        validateSurname(deliveryInfo.getSurname());
        validateAddress(deliveryInfo.getAddress());
        validateTelephone(deliveryInfo.getTelephone());
        
        return new Order(
            generateID(),
            email,
            emptySet(),
            OrderStatus.STARTED,
            BigDecimal.ZERO,
            deliveryInfo
        );
    }
}
