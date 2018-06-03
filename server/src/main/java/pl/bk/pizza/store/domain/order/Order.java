package pl.bk.pizza.store.domain.order;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.bk.pizza.store.domain.product.BaseProductInfo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import static pl.bk.pizza.store.domain.service.NowProvider.now;

@Getter
@Document(collection = "order")
public class Order
{
    @Id
    private final String id;
    private final String userEmail;
    private final Set<BaseProductInfo> products;
    private OrderStatus orderStatus;
    private LocalDateTime orderDateTime;
    private BigDecimal totalPrice;
    
    Order(String id, String userEmail, Set<BaseProductInfo> products, OrderStatus orderStatus, BigDecimal totalPrice)
    {
        this.id = id;
        this.userEmail = userEmail;
        this.products = products;
        this.orderStatus = orderStatus;
        this.totalPrice = totalPrice;
    }
    
    public Order addProduct(BaseProductInfo product)
    {
        products.add(product);
        return this;
    }
    
    public Order setToRealization()
    {
        orderStatus = OrderStatus.TO_REALIZATION;
        orderDateTime = now();
        totalPrice = calculateTotalPrice();
        return this;
    }
    
    private BigDecimal calculateTotalPrice()
    {
        return products
            .stream()
            .map(BaseProductInfo::getPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    //TODO validate if status is TO_REALIZATION
    public Order setToDelivered()
    {
        orderStatus = OrderStatus.DELIVERED;
        return this;
    }
}
