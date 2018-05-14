package pl.bk.pizza.store.domain.product;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Product<T extends ProductInfo>
{
    @Id
    private final String id;
    private final T productInfo;
    private BigDecimal price;
    private ProductStatus productStatus;
    
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
