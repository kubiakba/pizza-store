package pl.bk.pizza.store.domain.broker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.SneakyThrows;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pl.bk.pizza.store.application.dto.order.OrderDTO;
import pl.bk.pizza.store.domain.serializers.order.OrderDeserializer;

@Component
public class OrderListener
{
    @SneakyThrows
    @KafkaListener(topics = "${order.queue.topic}")
    public void listen(ConsumerRecord<String, String> record)
    {
        final ObjectMapper mapper = new ObjectMapper();
        final SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(OrderDTO.class, new OrderDeserializer());
        mapper.registerModule(simpleModule);
        OrderDTO order = mapper.readValue(record.value(), OrderDTO.class);
    }
}
