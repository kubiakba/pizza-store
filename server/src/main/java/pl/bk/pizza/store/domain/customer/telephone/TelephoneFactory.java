package pl.bk.pizza.store.domain.customer.telephone;

import org.springframework.stereotype.Component;

import static pl.bk.pizza.store.domain.validator.customer.TelephoneValidator.validateNumber;

@Component
public class TelephoneFactory
{
    public Telephone create(String number)
    {
        validateNumber(number);
        return new Telephone(number);
    }
}
