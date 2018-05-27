package pl.bk.pizza.store.domain.product;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

import static pl.bk.pizza.store.domain.service.IdGenerator.generateID;
import static pl.bk.pizza.store.domain.validator.product.ProductValidator.validatePrice;

@Getter
@Document(collection = "product")
public abstract class BaseProductInfo
{
    @Id
    private final String id;
    private BigDecimal price;
    private ProductStatus productStatus;
    
    protected BaseProductInfo(BigDecimal price)
    {
        validatePrice(price);
        this.id = generateID();
        this.price = price;
        this.productStatus = ProductStatus.AVAILABLE;
    }
    
    public boolean isAvailable()
    {
        return ProductStatus.AVAILABLE.equals(productStatus);
    }
    
    public void changePrice(BigDecimal price)
    {
        this.price = price;
    }
    
    public void makeNonavailable()
    {
        productStatus = ProductStatus.NONAVAILABLE;
    }
}
