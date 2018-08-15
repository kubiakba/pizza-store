package pl.bk.pizza.store.domain.serializers.order;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.SneakyThrows;
import pl.bk.pizza.store.application.dto.order.OrderDTO;

public class OrderDeserializer extends JsonDeserializer<OrderDTO>
{
    private final OrderDeserializerHelper orderDeserializerHelper = new OrderDeserializerHelper();
    
    @SneakyThrows
    @Override
    public OrderDTO deserialize(JsonParser p, DeserializationContext ctxt)
    {
        JsonNode node = p.getCodec().readTree(p);
        return orderDeserializerHelper.parseProduct(node);
    }
}


