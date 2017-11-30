package pl.bk.pizza.store.application.dto.order;

import lombok.Data;

import pl.bk.pizza.store.application.dto.order.discount.DiscountDTO;
import pl.bk.pizza.store.application.dto.product.ProductDTO;
import pl.bk.pizza.store.domain.order.Status;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class OrderDTO {

    private String id;
    private String userEmail;
    private Set<ProductDTO> products;
    private Status status;
    private BigDecimal totalPrice;
    private Set<DiscountDTO> discounts;
}
