package pl.bk.pizza.store.domain.broker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import pl.bk.pizza.store.domain.order.Order;

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
    
    public void send(Order order)
    {
        template.send(topic, order.toString());
    }
}
