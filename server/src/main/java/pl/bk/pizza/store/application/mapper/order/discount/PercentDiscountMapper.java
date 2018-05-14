package pl.bk.pizza.store.application.mapper.order.discount;

import org.springframework.stereotype.Component;
import pl.bk.pizza.store.application.dto.order.discount.PercentDiscountDTO;
import pl.bk.pizza.store.application.mapper.GenericMapper;
import pl.bk.pizza.store.domain.order.discount.PercentDiscount;

@Component
public class PercentDiscountMapper implements GenericMapper<PercentDiscountDTO, PercentDiscount>
{
    @Override
    public PercentDiscount mapFromDTO(PercentDiscountDTO discountDTO)
    {
        return new PercentDiscount(discountDTO.getDiscountPercent());
    }
    
    @Override
    public PercentDiscountDTO mapToDTO(PercentDiscount discount)
    {
        return new PercentDiscountDTO(discount.getDiscountPercent());
    }
}
