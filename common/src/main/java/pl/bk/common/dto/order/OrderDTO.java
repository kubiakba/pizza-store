package pl.bk.common.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.bk.common.dto.product.output.ProductDTO;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Getter
public class OrderDTO
{
    private String id;
    private String userEmail;
    private List<ProductDTO> products;
    private OrderStatusDTO orderStatus;
    private BigDecimal totalPrice;
    private DeliveryInfoDTO deliveryInfoDTO;
}
