package pl.bk.pizza.store.application.mapper.product;

import org.springframework.stereotype.Component;
import pl.bk.common.dto.product.PizzaSizeDTO;
import pl.bk.pizza.store.application.mapper.GenericMapper;
import pl.bk.pizza.store.domain.product.pizza.PizzaSize;

@Component
public class PizzaSizeMapper implements GenericMapper<PizzaSizeDTO,PizzaSize>
{
    @Override
    public PizzaSize mapFromDTO(PizzaSizeDTO pizzaSizeDTO)
    {
        return PizzaSize.valueOf(pizzaSizeDTO.name());
    }
    
    @Override
    public PizzaSizeDTO mapToDTO(PizzaSize pizzaSize)
    {
        return PizzaSizeDTO.valueOf(pizzaSize.name());
    }
}
