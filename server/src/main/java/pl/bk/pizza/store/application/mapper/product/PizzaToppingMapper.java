package pl.bk.pizza.store.application.mapper.product;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.bk.common.dto.product.output.PizzaToppingDTO;
import pl.bk.pizza.store.application.mapper.GenericMapper;
import pl.bk.pizza.store.domain.product.pizza.PizzaTopping;

@Component
@AllArgsConstructor
public class PizzaToppingMapper implements GenericMapper<PizzaToppingDTO, PizzaTopping>
{
    private final ProductStatusMapper productStatusMapper;
    
    @Override
    public PizzaTopping mapFromDTO(PizzaToppingDTO pizzaToppingDTO)
    {
        return new PizzaTopping(pizzaToppingDTO.getName(), pizzaToppingDTO.getPrice());
    }
    
    @Override
    public PizzaToppingDTO mapToDTO(PizzaTopping pizzaTopping)
    {
        return new PizzaToppingDTO(pizzaTopping.getId(), pizzaTopping.getPrice(), productStatusMapper.mapToDTO(pizzaTopping.getProductStatus()), pizzaTopping.getName());
    }
}
