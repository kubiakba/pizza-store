package pl.bk.pizza.store.domain.product;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static pl.bk.pizza.store.domain.service.IdGenerator.generateID;
import static pl.bk.pizza.store.domain.validator.product.ProductValidator.validatePrice;
import static pl.bk.pizza.store.domain.validator.product.ProductValidator.validateProductInfo;

@Service
public class ProductFactory<T extends ProductInfo>
{
    public Product<T> create(BigDecimal price, T productInfo)
    {
        validatePrice(price);
        validateProductInfo(productInfo);
        return new Product(generateID(), productInfo, price, ProductStatus.AVAILABLE);
    }
}
