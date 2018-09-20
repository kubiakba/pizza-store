package pl.bk.pizza.store.application.mapper.order;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.bk.common.dto.order.OrderDTO;
import pl.bk.common.dto.order.discount.DiscountDTO;
import pl.bk.common.dto.product.output.ProductDTO;
import pl.bk.pizza.store.application.mapper.ObjectToDtoMapper;
import pl.bk.pizza.store.application.mapper.product.ProductMapper;
import pl.bk.pizza.store.domain.order.Order;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@AllArgsConstructor
@Component
public class OrderMapper implements ObjectToDtoMapper<Order, OrderDTO>
{
    private final ProductMapper productMapper;
    private final DeliveryInfoMapper deliveryInfoMapper;
    private final OrderStatusMapper orderStatusMapper;
    private final DiscountMapper discountMapper;
    
    @Override
    public OrderDTO mapToDTO(Order order)
    {
        final List<ProductDTO> productDtos = order.getProducts()
                                                  .stream()
                                                  .map(productMapper::mapToDTO)
                                                  .collect(toList());
        
        final Set<DiscountDTO> discountDtos = order.getDiscounts()
                                                   .stream()
                                                   .map(discountMapper::mapToDTO)
                                                   .collect(Collectors.toSet());
        
        return new OrderDTO(
            order.getId(),
            order.getUserEmail(),
            productDtos,
            orderStatusMapper.mapToDTO(order.getOrderStatus()),
            order.getTotalPrice(),
            deliveryInfoMapper.mapToDTO(order.getDeliveryInfo()),
            discountDtos,
            order.getTotalPriceWithDiscounts()
        );
    }
}
