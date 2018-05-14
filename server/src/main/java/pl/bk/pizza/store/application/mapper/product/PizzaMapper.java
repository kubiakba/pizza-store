package pl.bk.pizza.store.application.mapper.product;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.bk.pizza.store.application.dto.product.PizzaDTO;
import pl.bk.pizza.store.application.dto.product.ProductDTO;
import pl.bk.pizza.store.domain.product.Product;
import pl.bk.pizza.store.domain.product.ProductFactory;
import pl.bk.pizza.store.domain.product.pizza.Pizza;

@AllArgsConstructor
@Component
public class PizzaMapper implements ProductMapper<PizzaDTO, Pizza>
{
    private final ProductFactory<Pizza> factory;
    
    @Override
    public Product<Pizza> mapFromDTO(ProductDTO<PizzaDTO> pizzaDto)
    {
        final Pizza pizza = new Pizza(pizzaDto.getProductInfo().getSize(), pizzaDto.getProductInfo().getDough());
        return factory.create(pizzaDto.getPrice(), pizza);
    }
    
    @Override
    public ProductDTO<PizzaDTO> mapToDTO(Product<Pizza> pizza)
    {
        final PizzaDTO pizzaDto = new PizzaDTO(pizza.getProductInfo().getSize(), pizza.getProductInfo().getDough());
        return new ProductDTO<>(pizza.getId(), pizza.getPrice(), pizza.getProductStatus(), pizzaDto);
    }
}
