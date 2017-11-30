package pl.bk.pizza.store.application.dto.product;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class NewProductDTO {

    private BigDecimal price;
    @JsonTypeInfo(use= JsonTypeInfo.Id.MINIMAL_CLASS, include= JsonTypeInfo.As.PROPERTY, property="@class")
    private ProductInfoDTO productInfo;
}
