package pl.bk.pizza.store.domain.serializers.order;

import com.fasterxml.jackson.databind.JsonNode;
import pl.bk.pizza.store.application.dto.order.AddressDTO;
import pl.bk.pizza.store.application.dto.order.DeliveryInfoDTO;
import pl.bk.pizza.store.application.dto.order.TelephoneDTO;

public class DeliveryInfoDeserializerHelper
{
    public DeliveryInfoDTO parseDeliveryInfoDTO(JsonNode node)
    {
        return new DeliveryInfoDTO(
            node.get("name").asText(),
            node.get("surname").asText(),
            parseAddressDTO(node.get("addressDTO")),
            parseTelephoneDTO(node.get("telephoneDTO"))
        );
    }
    
    private AddressDTO parseAddressDTO(JsonNode node)
    {
        return new AddressDTO(
            node.get("city").asText(),
            node.get("street").asText(),
            node.get("streetNumber").asText(),
            node.get("postCode").asText()
        );
    }
    
    private TelephoneDTO parseTelephoneDTO(JsonNode node)
    {
        return new TelephoneDTO(node.get("number").asText());
    }
}