package pl.bk.pizza.store.application.mapper.order;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.bk.pizza.store.application.dto.order.NewOrderDTO;
import pl.bk.pizza.store.application.mapper.DtoToObjectMapper;
import pl.bk.pizza.store.domain.order.Order;
import pl.bk.pizza.store.domain.order.OrderFactory;

@AllArgsConstructor
@Component
public class NewOrderMapper implements DtoToObjectMapper<NewOrderDTO, Order>
{
    private final OrderFactory factory;
    
    @Override
    public Order mapFromDTO(NewOrderDTO newOrderDTO)
    {
        if(newOrderDTO.getEmail() != null)
        {
            return factory.create(newOrderDTO.getEmail());
        }
        else
        {
            return factory.create();
        }
    }
}
