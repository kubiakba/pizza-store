package pl.bk.pizza.store.application.mapper.product;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.bk.pizza.store.application.dto.product.PizzaDTO;
import pl.bk.pizza.store.application.dto.product.ProductDTO;
import pl.bk.pizza.store.application.mapper.GenericMapper;
import pl.bk.pizza.store.domain.product.Product;
import pl.bk.pizza.store.domain.product.kebab.Kebab;
import pl.bk.pizza.store.domain.product.pizza.Pizza;
import pl.bk.pizza.store.domain.product.pizza.PizzaTopping;

@AllArgsConstructor
@Component
public class GenericProductMapper implements GenericMapper<ProductDTO, Product>
{
    private final PizzaMapper pizzaMapper;
    private final PizzaToppingMapper pizzaToppingMapper;
    private final KebabMapper kebabMapper;
    
    @Override
    public Product mapFromDTO(ProductDTO productDto)
    {
        final Class clazz = productDto.getProductInfo().getClass();
        
        if(Pizza.class.equals(clazz))
        {
            return pizzaMapper.mapFromDTO(productDto);
        }
        
        if(PizzaTopping.class.equals(clazz))
        {
            return pizzaToppingMapper.mapFromDTO(productDto);
        }
        
        if(Kebab.class.equals(clazz))
        {
            return kebabMapper.mapFromDTO(productDto);
        }
        
        throw new IllegalArgumentException("Passed argument is null or cannot be parsed: " + productDto);
    }
    
    @Override
    public ProductDTO mapToDTO(Product product)
    {
        final Class clazz = product.getProductInfo().getClass();
        
        if(PizzaDTO.class.equals(clazz))
        {
            return pizzaMapper.mapToDTO(product);
        }
        
        if(PizzaTopping.class.equals(clazz))
        {
            return pizzaToppingMapper.mapToDTO(product);
        }
        
        if(Kebab.class.equals(clazz))
        {
            return kebabMapper.mapToDTO(product);
        }
        
        throw new IllegalArgumentException("Passed argument is null or cannot be parsed: " + product);
    }
}
