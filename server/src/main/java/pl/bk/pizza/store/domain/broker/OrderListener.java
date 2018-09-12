package pl.bk.pizza.store.domain.broker;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.searchbox.client.JestClient;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pl.bk.common.dto.order.OrderDTO;
import pl.bk.search.SearchlyService;

@Component
@AllArgsConstructor
public class OrderListener
{
    private final SearchlyService searchlyService;
    private final JestClient client;
    private final ObjectMapper mapper;
    
    @SneakyThrows
    @KafkaListener(topics = "${order.queue.topic}")
    public void listen(ConsumerRecord<String, String> record)
    {
        OrderDTO order = mapper.readValue(record.value(), OrderDTO.class);
        searchlyService.addOrder(client, order);
    }
}
