package pl.bk.pizza.store.application.mapper.product;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.bk.pizza.store.application.dto.product.NewProductDTO;
import pl.bk.pizza.store.application.mapper.DtoToObjectMapper;
import pl.bk.pizza.store.domain.product.Product;
import pl.bk.pizza.store.domain.product.ProductFactory;
import pl.bk.pizza.store.domain.product.ProductInfo;

@AllArgsConstructor
@Component
public class NewProductMapper implements DtoToObjectMapper<NewProductDTO, Product>
{
    private final ProductFactory factory;
    
    @Override
    public Product mapFromDTO(NewProductDTO newProductDTO)
    {
        return factory.create(newProductDTO.getPrice(), (ProductInfo) newProductDTO.getProductInfo());
    }
}
