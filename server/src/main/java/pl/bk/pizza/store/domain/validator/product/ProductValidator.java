package pl.bk.pizza.store.domain.validator.product;

import pl.bk.pizza.store.domain.exception.InvalidEntityException;
import pl.bk.pizza.store.domain.exception.MissingEntityException;
import pl.bk.pizza.store.domain.product.BaseProductInfo;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

import static pl.bk.pizza.store.domain.exception.ErrorCode.EMPTY_PRODUCT_PRICE;
import static pl.bk.pizza.store.domain.exception.ErrorCode.MISSING_PRODUCT;
import static pl.bk.pizza.store.domain.exception.Preconditions.check;

public class ProductValidator
{
    public static void validatePrice(BigDecimal price)
    {
        check(price == null, () -> new InvalidEntityException(
            "Product price is null.",
            EMPTY_PRODUCT_PRICE
        ));
    }
    
    public static Mono<BaseProductInfo> productShouldExists(String productId)
    {
        return Mono.error(new MissingEntityException(
            "Product with id: " + productId + "does not exists",
            MISSING_PRODUCT
        ));
    }
}
