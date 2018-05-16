package pl.bk.pizza.store.domain.customer;

import lombok.Getter;

import static pl.bk.pizza.store.domain.validator.customer.TelephoneValidator.validateNumber;

@Getter
public class Telephone
{
    private final String number;
    
    public Telephone(String number)
    {
        validateNumber(number);
        this.number = number;
    }
}
