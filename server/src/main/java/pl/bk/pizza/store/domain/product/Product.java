package pl.bk.pizza.store.domain.product;

import org.springframework.data.annotation.Id;

import pl.bk.pizza.store.domain.exception.InvalidEntityException;

import java.math.BigDecimal;

import static pl.bk.pizza.store.domain.exception.ErrorCode.*;
import static pl.bk.pizza.store.domain.exception.Preconditions.check;

public class Product {

    @Id
    private final String id;
    private BigDecimal price;
    private Status status;
    private final ProductInfo productInfo;

    Product(String id, BigDecimal price, ProductInfo productInfo) {
        validatePrice(price);
        validateProductInfo(productInfo);
        this.status = Status.AVAILABLE;
        this.id = id;
        this.productInfo = productInfo;
        this.price = new BigDecimal(price.toString());
    }

    private void validatePrice(BigDecimal price) {
        check((price == null), () -> new InvalidEntityException("Product price is null.", EMPTY_PRODUCT_PRICE));
    }

    private void validateProductInfo(ProductInfo productInfo) {
        check((productInfo == null), () -> new InvalidEntityException("ProductInfo is null.", EMPTY_PRODUCT_INFO));
    }

    public BigDecimal getPrice(){
        return price;
    }

    public ProductInfo getProductInfo(){
        return productInfo;
    }

    public String getId() { return id; }

    public boolean isAvailable() {
        return Status.AVAILABLE.equals(status);
    }

    public void changePrice(BigDecimal price){
        this.price = price;
    }

    public void makeNonavailable(){ status = Status.NONAVAILABLE; }

    public Status getStatus() {
        return status;
    }
}
