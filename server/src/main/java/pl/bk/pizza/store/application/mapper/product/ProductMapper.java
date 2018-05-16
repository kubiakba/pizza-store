package pl.bk.pizza.store.application.mapper.product;

import org.springframework.stereotype.Component;
import pl.bk.pizza.store.application.dto.product.out.KebabDTO;
import pl.bk.pizza.store.application.dto.product.out.PizzaDTO;
import pl.bk.pizza.store.application.dto.product.out.PizzaToppingDTO;
import pl.bk.pizza.store.application.dto.product.out.ProductDTO;
import pl.bk.pizza.store.application.mapper.ObjectToDtoMapper;
import pl.bk.pizza.store.domain.product.BaseProductInfo;
import pl.bk.pizza.store.domain.product.kebab.Kebab;
import pl.bk.pizza.store.domain.product.pizza.Pizza;
import pl.bk.pizza.store.domain.product.pizza.PizzaTopping;

@Component
public class ProductMapper implements ObjectToDtoMapper<BaseProductInfo, ProductDTO>
{
    @Override
    public ProductDTO mapToDTO(BaseProductInfo productInfo)
    {
        if(productInfo instanceof Pizza)
        {
            Pizza pizza = (Pizza) productInfo;
            return new PizzaDTO(pizza.getId(), pizza.getPrice(), pizza.getProductStatus(), pizza.getSize(), pizza.getDough());
        }
        
        if(productInfo instanceof PizzaTopping)
        {
            PizzaTopping pizzaTopping = (PizzaTopping) productInfo;
            return new PizzaToppingDTO(pizzaTopping.getId(), pizzaTopping.getPrice(), pizzaTopping.getProductStatus(), pizzaTopping.getName());
        }
        
        if(productInfo instanceof Kebab)
        {
            Kebab kebab = (Kebab) productInfo;
            return new KebabDTO(kebab.getId(), kebab.getPrice(), kebab.getProductStatus(), kebab.getName(), kebab.getDescription());
        }
        
        throw new IllegalArgumentException("Passed argument is null or cannot be parsed: " + productInfo);
        
    }
}
