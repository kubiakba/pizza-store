package pl.bk.pizza.store.application.mapper.product;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.bk.common.dto.product.input.NewKebabDTO;
import pl.bk.common.dto.product.input.NewPizzaDTO;
import pl.bk.common.dto.product.input.NewPizzaToppingDTO;
import pl.bk.common.dto.product.input.NewProductDTO;
import pl.bk.pizza.store.application.mapper.DtoToObjectMapper;
import pl.bk.pizza.store.domain.exception.UnsupportedTypeException;
import pl.bk.pizza.store.domain.product.BaseProductInfo;
import pl.bk.pizza.store.domain.product.kebab.Kebab;
import pl.bk.pizza.store.domain.product.pizza.Pizza;
import pl.bk.pizza.store.domain.product.pizza.PizzaTopping;

import static pl.bk.pizza.store.domain.exception.ErrorCode.UNPROCESSABLE_TYPE;

@Component
@AllArgsConstructor
public class NewProductMapper implements DtoToObjectMapper<NewProductDTO, BaseProductInfo>
{
    private final DoughMapper doughMapper;
    private final PizzaSizeMapper pizzaSizeMapper;
    
    @Override
    public BaseProductInfo mapFromDTO(NewProductDTO newProductDTO)
    {
        if(newProductDTO instanceof NewPizzaDTO)
        {
            NewPizzaDTO pizzaDto = (NewPizzaDTO) newProductDTO;
            return new Pizza(pizzaSizeMapper.mapFromDTO(pizzaDto.getSize()), doughMapper.mapFromDTO(pizzaDto.getDough()), pizzaDto.getPrice());
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
