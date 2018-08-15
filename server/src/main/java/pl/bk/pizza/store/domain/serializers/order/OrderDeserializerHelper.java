package pl.bk.pizza.store.domain.serializers.order;

import com.fasterxml.jackson.databind.JsonNode;
import pl.bk.pizza.store.application.dto.order.DeliveryInfoDTO;
import pl.bk.pizza.store.application.dto.order.OrderDTO;
import pl.bk.pizza.store.application.dto.product.output.ProductDTO;
import pl.bk.pizza.store.domain.order.OrderStatus;
import pl.bk.pizza.store.domain.serializers.product.ProductDeserializerHelper;

import java.math.BigDecimal;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static java.util.stream.StreamSupport.stream;

public class OrderDeserializerHelper
{
    private final ProductDeserializerHelper productHelper = new ProductDeserializerHelper();
    private final DeliveryInfoDeserializerHelper deliveryHelper = new DeliveryInfoDeserializerHelper();
    
    public OrderDTO parseProduct(JsonNode node)
    {
        final String id = node.get("id").asText();
        final String userEmail = node.get("userEmail").asText();
        
        final List<ProductDTO> products = stream(node.get("products").spliterator(), false)
            .map(productHelper::parseProduct)
            .collect(toList());
        
        final OrderStatus status = OrderStatus.valueOf(node.get("orderStatus").asText());
        final BigDecimal totalPrice = new BigDecimal(node.get("totalPrice").asText());
        final DeliveryInfoDTO deliveryInfoDTO = deliveryHelper.parseDeliveryInfoDTO(node.get("deliveryInfoDTO"));
        
        return new OrderDTO(id, userEmail, products, status, totalPrice, deliveryInfoDTO);
    }
}
