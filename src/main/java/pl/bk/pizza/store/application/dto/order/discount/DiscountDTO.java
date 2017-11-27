package pl.bk.pizza.store.application.dto.order.discount;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use= JsonTypeInfo.Id.MINIMAL_CLASS, include= JsonTypeInfo.As.PROPERTY, property="@class")
public interface DiscountDTO {
}
