package pl.bk.pizza.store.domain.order;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import pl.bk.pizza.store.domain.order.discount.Discount;
import pl.bk.pizza.store.domain.product.BaseProductInfo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import static pl.bk.pizza.store.domain.service.NowProvider.now;

@Getter
public class Order
{
    @Id
    private final String id;
    private final String userEmail;
    private final Set<BaseProductInfo> products;
    private final Set<Discount> discounts;
    private OrderStatus orderStatus;
    private LocalDateTime orderDateTime;
    private BigDecimal totalPrice;
    
    Order(String id, String userEmail, Set<BaseProductInfo> products, Set<Discount> discounts, OrderStatus orderStatus, BigDecimal totalPrice)
    {
        this.id = id;
        this.userEmail = userEmail;
        this.products = products;
        this.discounts = discounts;
        this.orderStatus = orderStatus;
        this.totalPrice = totalPrice;
    }
    
    public void addProduct(BaseProductInfo product)
    {
        products.add(product);
    }
    
    public void setToRealization()
    {
        orderStatus = OrderStatus.TO_REALIZATION;
        orderDateTime = now();
        totalPrice = calculateTotalPrice();
    }
    
    private BigDecimal calculateTotalPrice()
    {
        return products
            .stream()
            .map(BaseProductInfo::getPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    //TODO validate if status is TO_REALIZATION
    public void setToDelivered()
    {
        orderStatus = OrderStatus.DELIVERED;
    }
    
    public void addDiscount(Discount discount)
    {
        discounts.add(discount);
    }
}
