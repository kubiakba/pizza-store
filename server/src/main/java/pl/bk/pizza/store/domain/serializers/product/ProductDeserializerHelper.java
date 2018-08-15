package pl.bk.pizza.store.domain.serializers.product;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;
import pl.bk.pizza.store.application.dto.product.output.KebabDTO;
import pl.bk.pizza.store.application.dto.product.output.PizzaDTO;
import pl.bk.pizza.store.application.dto.product.output.PizzaToppingDTO;
import pl.bk.pizza.store.application.dto.product.output.ProductDTO;
import pl.bk.pizza.store.domain.exception.UnsupportedTypeException;
import pl.bk.pizza.store.domain.product.ProductStatus;
import pl.bk.pizza.store.domain.product.kebab.Kebab;
import pl.bk.pizza.store.domain.product.pizza.Dough;
import pl.bk.pizza.store.domain.product.pizza.Pizza;
import pl.bk.pizza.store.domain.product.pizza.PizzaSize;
import pl.bk.pizza.store.domain.product.pizza.PizzaTopping;

import java.math.BigDecimal;

import static pl.bk.pizza.store.domain.exception.ErrorCode.UNPROCESSABLE_TYPE;

@Component
public class ProductDeserializerHelper
{
    public ProductDTO parseProduct(JsonNode node)
    {
        if(PizzaTopping.class.getSimpleName().equals(getProductType(node)))
        {
            return new PizzaToppingDTO(
                node.get("id").asText(),
                new BigDecimal(node.get("price").asText()),
                ProductStatus.valueOf(node.get("productStatus").asText()),
                node.get("name").asText()
            );
        }
        if(Pizza.class.getSimpleName().equals(getProductType(node)))
        {
            return new PizzaDTO(
                node.get("id").asText(),
                new BigDecimal(node.get("price").asText()),
                ProductStatus.valueOf(node.get("productStatus").asText()),
                PizzaSize.valueOf(node.get("size").asText()),
                Dough.valueOf(node.get("dough").asText())
            );
        }
        if(Kebab.class.getSimpleName().equals(getProductType(node)))
        {
            return new KebabDTO(
                node.get("id").asText(),
                new BigDecimal(node.get("price").asText()),
                ProductStatus.valueOf(node.get("productStatus").asText()),
                node.get("name").asText(),
                node.get("description").asText()
            );
        }
        throw new UnsupportedTypeException("Passed argument is null or cannot be parsed: " + node.toString(), UNPROCESSABLE_TYPE);
    }
    
    private String getProductType(JsonNode node)
    {
        return node.get("type").asText();
    }
}
