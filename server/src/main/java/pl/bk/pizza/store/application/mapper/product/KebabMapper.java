package pl.bk.pizza.store.application.mapper.product;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.bk.pizza.store.application.dto.product.KebabDTO;
import pl.bk.pizza.store.application.dto.product.ProductDTO;
import pl.bk.pizza.store.domain.product.Product;
import pl.bk.pizza.store.domain.product.ProductFactory;
import pl.bk.pizza.store.domain.product.kebab.Kebab;

@AllArgsConstructor
@Component
public class KebabMapper implements ProductMapper<KebabDTO, Kebab>
{
    private final ProductFactory<Kebab> factory;
    
    @Override
    public Product<Kebab> mapFromDTO(ProductDTO<KebabDTO> kebabDto)
    {
        final Kebab kebab = new Kebab(kebabDto.getProductInfo().getDescription(), kebabDto.getProductInfo().getDescription());
        return factory.create(kebabDto.getPrice(), kebab);
    }
    
    @Override
    public ProductDTO<KebabDTO> mapToDTO(Product<Kebab> kebab)
    {
        final KebabDTO kebabDTO = new KebabDTO(kebab.getProductInfo().getDescription(), kebab.getProductInfo().getName());
        return new ProductDTO<>(kebab.getId(), kebab.getPrice(), kebab.getProductStatus(), kebabDTO);
    }
}
