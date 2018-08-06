package pl.bk.pizza.store.application.mapper.order;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.bk.pizza.store.application.dto.order.OrderDTO;
import pl.bk.pizza.store.application.dto.product.output.ProductDTO;
import pl.bk.pizza.store.application.mapper.ObjectToDtoMapper;
import pl.bk.pizza.store.application.mapper.product.ProductMapper;
import pl.bk.pizza.store.domain.order.Order;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

@AllArgsConstructor
@Component
public class OrderMapper implements ObjectToDtoMapper<Order, OrderDTO>
{
    private final ProductMapper productMapper;
    private final DeliveryInfoMapper deliveryInfoMapper;
    
    @Override
    public OrderDTO mapToDTO(Order order)
    {
        final Set<ProductDTO> productDtos = order.getProducts()
                                                 .stream()
                                                 .map(productMapper::mapToDTO)
                                                 .collect(toSet());
        
        return new OrderDTO(
            order.getId(),
            order.getUserEmail(),
            productDtos,
            order.getOrderStatus(),
            order.getTotalPrice(),
            deliveryInfoMapper.mapToDTO(order.getDeliveryInfo())
        );
    }
}
