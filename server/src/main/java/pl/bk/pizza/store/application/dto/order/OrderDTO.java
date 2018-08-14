package pl.bk.pizza.store.application.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.bk.pizza.store.application.dto.product.output.ProductDTO;
import pl.bk.pizza.store.domain.order.OrderStatus;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Getter
public class OrderDTO
{
    private String id;
    private String userEmail;
    private List<ProductDTO> products;
    private OrderStatus orderStatus;
    private BigDecimal totalPrice;
    private DeliveryInfoDTO deliveryInfoDTO;
}
