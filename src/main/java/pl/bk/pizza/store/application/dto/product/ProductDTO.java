package pl.bk.pizza.store.application.dto.product;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.Data;

import pl.bk.pizza.store.domain.product.Status;

import java.math.BigDecimal;

@Data
public class ProductDTO {

    private String id;
    private BigDecimal price;
    private Status status;
    @JsonTypeInfo(use= JsonTypeInfo.Id.MINIMAL_CLASS, include= JsonTypeInfo.As.PROPERTY, property="@class")
    private ProductInfoDTO productInfo;
}
