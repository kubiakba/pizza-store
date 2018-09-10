package pl.bk.pizza.store.application.mapper.product;

import org.springframework.stereotype.Component;
import pl.bk.common.dto.product.ProductStatusDTO;
import pl.bk.pizza.store.application.mapper.GenericMapper;
import pl.bk.pizza.store.domain.product.ProductStatus;

@Component
public class ProductStatusMapper implements GenericMapper<ProductStatusDTO, ProductStatus>
{
    @Override
    public ProductStatus mapFromDTO(ProductStatusDTO productStatusDTO)
    {
        return ProductStatus.valueOf(productStatusDTO.name());
    }
    
    @Override
    public ProductStatusDTO mapToDTO(ProductStatus productStatus)
    {
        return ProductStatusDTO.valueOf(productStatus.name());
    }
}
