package pl.bk.pizza.store.domain.validator.product;

import pl.bk.pizza.store.domain.exception.InvalidEntityException;
import pl.bk.pizza.store.domain.product.ProductInfo;

import java.math.BigDecimal;

import static pl.bk.pizza.store.domain.exception.ErrorCode.EMPTY_PRODUCT_INFO;
import static pl.bk.pizza.store.domain.exception.ErrorCode.EMPTY_PRODUCT_PRICE;
import static pl.bk.pizza.store.domain.exception.Preconditions.check;

public class ProductValidator
{
    public static void validatePrice(BigDecimal price)
    {
        check((price == null), () -> new InvalidEntityException("Product price is null.", EMPTY_PRODUCT_PRICE));
    }
    
    public static void validateProductInfo(ProductInfo productInfo)
    {
        check((productInfo == null), () -> new InvalidEntityException("ProductInfo is null.", EMPTY_PRODUCT_INFO));
    }
    
}
