package pl.bk.pizza.store.domain.customer.telephone;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Telephone
{
    private final String number;
}
