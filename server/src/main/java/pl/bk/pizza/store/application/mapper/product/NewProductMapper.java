package pl.bk.pizza.store.application.mapper.product;

import org.springframework.stereotype.Component;
import pl.bk.pizza.store.application.dto.product.input.NewKebabDTO;
import pl.bk.pizza.store.application.dto.product.input.NewPizzaDTO;
import pl.bk.pizza.store.application.dto.product.input.NewPizzaToppingDTO;
import pl.bk.pizza.store.application.dto.product.input.NewProductDTO;
import pl.bk.pizza.store.application.mapper.DtoToObjectMapper;
import pl.bk.pizza.store.domain.exception.UnsupportedTypeException;
import pl.bk.pizza.store.domain.product.BaseProductInfo;
import pl.bk.pizza.store.domain.product.kebab.Kebab;
import pl.bk.pizza.store.domain.product.pizza.Pizza;
import pl.bk.pizza.store.domain.product.pizza.PizzaTopping;

import static pl.bk.pizza.store.domain.exception.ErrorCode.UNPROCESSABLE_TYPE;

@Component
public class NewProductMapper implements DtoToObjectMapper<NewProductDTO, BaseProductInfo>
{
    @Override
    public BaseProductInfo mapFromDTO(NewProductDTO newProductDTO)
    {
        if(newProductDTO instanceof NewPizzaDTO)
        {
            NewPizzaDTO pizzaDto = (NewPizzaDTO) newProductDTO;
            return new Pizza(pizzaDto.getSize(), pizzaDto.getDough(), pizzaDto.getPrice());
        }
        
        if(newProductDTO instanceof NewPizzaToppingDTO)
        {
            NewPizzaToppingDTO pizzaToppingDto = (NewPizzaToppingDTO) newProductDTO;
            return new PizzaTopping(pizzaToppingDto.getToppingName(), pizzaToppingDto.getPrice());
        }
        
        if(newProductDTO instanceof NewKebabDTO)
        {
            NewKebabDTO kebabDto = (NewKebabDTO) newProductDTO;
            return new Kebab(kebabDto.getDescription(), kebabDto.getKebabName(), kebabDto.getPrice());
        }
        
        throw new UnsupportedTypeException("Passed argument is null or cannot be parsed: " + newProductDTO, UNPROCESSABLE_TYPE);
    }
}
