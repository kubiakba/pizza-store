package pl.bk.pizza.store.application.mapper.order;

import org.springframework.stereotype.Component;
import pl.bk.common.dto.order.OrderStatusDTO;
import pl.bk.pizza.store.application.mapper.GenericMapper;
import pl.bk.pizza.store.domain.order.OrderStatus;

@Component
public class OrderStatusMapper implements GenericMapper<OrderStatusDTO, OrderStatus>
{
    @Override
    public OrderStatus mapFromDTO(OrderStatusDTO orderStatusDTO)
    {
        return OrderStatus.valueOf(orderStatusDTO.name());
    }
    
    @Override
    public OrderStatusDTO mapToDTO(OrderStatus orderStatus)
    {
        return OrderStatusDTO.valueOf(orderStatus.name());
    }
}
