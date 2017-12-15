package pl.bk.pizza.store.domain.order;

import org.springframework.data.annotation.Id;

import pl.bk.pizza.store.domain.exception.ErrorCode;
import pl.bk.pizza.store.domain.exception.InvalidEntityException;
import pl.bk.pizza.store.domain.order.discount.Discount;
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
    private Set<Discount> discounts;
    private BigDecimal totalPrice;

    Order(String id, String userEmail){
        validateUserEmail(userEmail);
        this.id = id;
        this.userEmail = userEmail;
        totalPrice = BigDecimal.ZERO;
        status = Status.STARTED;
        products = new HashSet<>();
        discounts = new HashSet<>();
    }

    private void validateUserEmail(String userEmail) {
        check(isBlank(userEmail), () -> new InvalidEntityException(("UserEmail is null or empty."),
            ErrorCode.EMPTY_USER_EMAIL));
    }

    public void addProduct(Product product){
        products.add(product);
    }

    public BigDecimal calculateTotalPrice(){

        BigDecimal priceToPay = products.stream()
           .map(Product::getPrice)
           .reduce(BigDecimal.ZERO, BigDecimal::add);

        for(Discount discount :discounts){
            priceToPay = discount.calculateDiscount(priceToPay);
        }

        return priceToPay;
    }

    public void setToRealization(){
        status = Status.TO_REALIZATION;
        orderDateTime = LocalDateTime.now(ZoneId.of(DEFAULT_ZONE_NAME));
        totalPrice = calculateTotalPrice();
    }

    public void setToDelivered(){
        status = Status.DELIVERED;
    }

    public void addDiscount(Discount discount){
        discounts.add(discount);
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

    public Set<Discount> getDiscounts() {
        return discounts;
    }

    public BigDecimal getTotalPrice() { return totalPrice; }
}


