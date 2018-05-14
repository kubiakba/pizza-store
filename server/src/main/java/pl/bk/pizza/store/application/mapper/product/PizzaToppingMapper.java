package pl.bk.pizza.store.application.mapper.product;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.bk.pizza.store.application.dto.product.PizzaToppingDTO;
import pl.bk.pizza.store.application.dto.product.ProductDTO;
import pl.bk.pizza.store.domain.product.Product;
import pl.bk.pizza.store.domain.product.ProductFactory;
import pl.bk.pizza.store.domain.product.pizza.PizzaTopping;

@AllArgsConstructor
@Component
public class PizzaToppingMapper implements ProductMapper<PizzaToppingDTO, PizzaTopping>
{
    ProductFactory<PizzaTopping> factory;
    
    @Override
    public Product<PizzaTopping> mapFromDTO(ProductDTO<PizzaToppingDTO> pizzaToppingDto)
    {
        final PizzaTopping pizzaTopping = new PizzaTopping(pizzaToppingDto.getProductInfo().getName());
        return factory.create(pizzaToppingDto.getPrice(), pizzaTopping);
    }
    
    @Override
    public ProductDTO<PizzaToppingDTO> mapToDTO(Product<PizzaTopping> pizzaTopping)
    {
        final PizzaToppingDTO pizzaToppingDto = new PizzaToppingDTO(pizzaTopping.getProductInfo().getName());
        return new ProductDTO<>(pizzaTopping.getId(), pizzaTopping.getPrice(), pizzaTopping.getProductStatus(), pizzaToppingDto);
    }
}
