package pl.bk.pizza.store.domain.order;

import org.springframework.data.annotation.Id;

import pl.bk.pizza.store.domain.exception.ErrorCode;
import pl.bk.pizza.store.domain.exception.InvalidEntityException;
import pl.bk.pizza.store.domain.product.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;

import static org.apache.commons.lang.StringUtils.*;
import static pl.bk.pizza.store.domain.exception.Preconditions.*;

public class Order {

    private static final String DEFAULT_ZONE_NAME = "Europe/Warsaw";

    @Id
    private final String id;
    private final String userEmail;
    private final Set<Product> products;
    private Status status;
    private LocalDateTime orderDateTime;

    Order(String id, String userEmail){
        validateUserEmail(userEmail);
        this.id = id;
        this.userEmail = userEmail;
        status = Status.STARTED;
        products = new HashSet<>();
    }

    private void validateUserEmail(String userEmail) {
        check(isBlank(userEmail), () -> new InvalidEntityException(("UserEmail is null or empty."),
            ErrorCode.EMPTY_USER_EMAIL));
    }

    public void addProduct(Product product){
        products.add(product);
    }

    public BigDecimal calculateTotalPrice(){
        return products.stream()
            .map(Product::getPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void setToRealization(){
        status = Status.TO_REALIZATION;
        orderDateTime = LocalDateTime.now(ZoneId.of(DEFAULT_ZONE_NAME));
    }

    public String getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public Set<Product> getProducts() {
        return products;
    }
}


