package pl.bk.pizza.store.domain.broker;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import pl.bk.pizza.store.application.dto.order.OrderDTO;

@Component
public class OrderQueue
{
    private final KafkaTemplate<String, String> template;
    
    @Value("${order.queue.topic}")
    private String topic;
    
    public OrderQueue(KafkaTemplate<String, String> template)
    {
        this.template = template;
    }
    
    @SneakyThrows
    public void send(OrderDTO order)
    {
        template.send(topic, new ObjectMapper().writeValueAsString(order));
    }
}
