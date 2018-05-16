package pl.bk.pizza.store.application.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.bk.pizza.store.application.dto.order.discount.DiscountDTO;
import pl.bk.pizza.store.application.dto.product.out.ProductDTO;
import pl.bk.pizza.store.domain.order.OrderStatus;

import java.math.BigDecimal;
import java.util.Set;

@AllArgsConstructor
@Getter
public class OrderDTO
{
    private String id;
    private String userEmail;
    private Set<ProductDTO> products;
    private OrderStatus orderStatus;
    private BigDecimal totalPrice;
    private Set<DiscountDTO> discounts;
}
