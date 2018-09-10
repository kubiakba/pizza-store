package pl.bk.pizza.store.application.mapper.product;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.bk.common.dto.product.output.KebabDTO;
import pl.bk.common.dto.product.output.PizzaDTO;
import pl.bk.common.dto.product.output.PizzaToppingDTO;
import pl.bk.common.dto.product.output.ProductDTO;
import pl.bk.pizza.store.application.mapper.ObjectToDtoMapper;
import pl.bk.pizza.store.domain.exception.UnsupportedTypeException;
import pl.bk.pizza.store.domain.product.BaseProductInfo;
import pl.bk.pizza.store.domain.product.kebab.Kebab;
import pl.bk.pizza.store.domain.product.pizza.Pizza;
import pl.bk.pizza.store.domain.product.pizza.PizzaTopping;

import static pl.bk.pizza.store.domain.exception.ErrorCode.UNPROCESSABLE_TYPE;

@Component
@AllArgsConstructor
public class ProductMapper implements ObjectToDtoMapper<BaseProductInfo, ProductDTO>
{
    private final ProductStatusMapper productStatusMapper;
    private final PizzaSizeMapper pizzaSizeMapper;
    private final DoughMapper doughMapper;
    
    @Override
    public ProductDTO mapToDTO(BaseProductInfo productInfo)
    {
        if(productInfo instanceof Pizza)
        {
            Pizza pizza = (Pizza) productInfo;
            return new PizzaDTO(
                pizza.getId(),
                pizza.getPrice(),
                productStatusMapper.mapToDTO(pizza.getProductStatus()),
                pizzaSizeMapper.mapToDTO(pizza.getSize()),
                doughMapper.mapToDTO(pizza.getDough())
            );
        }
        
        if(productInfo instanceof PizzaTopping)
        {
            PizzaTopping pizzaTopping = (PizzaTopping) productInfo;
            return new PizzaToppingDTO(pizzaTopping.getId(), pizzaTopping.getPrice(), productStatusMapper.mapToDTO(pizzaTopping.getProductStatus()), pizzaTopping.getName());
        }
        
        if(productInfo instanceof Kebab)
        {
            Kebab kebab = (Kebab) productInfo;
            return new KebabDTO(kebab.getId(), kebab.getPrice(), productStatusMapper.mapToDTO(kebab.getProductStatus()), kebab.getName(), kebab.getDescription());
        }
        
        throw new UnsupportedTypeException("Passed argument is null or cannot be parsed: " + productInfo, UNPROCESSABLE_TYPE);
    }
}
