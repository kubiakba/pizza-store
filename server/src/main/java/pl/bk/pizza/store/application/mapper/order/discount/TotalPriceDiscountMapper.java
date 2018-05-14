package pl.bk.pizza.store.application.mapper.order.discount;

import org.springframework.stereotype.Component;
import pl.bk.pizza.store.application.dto.order.discount.TotalPriceDiscountDTO;
import pl.bk.pizza.store.application.mapper.GenericMapper;
import pl.bk.pizza.store.domain.order.discount.TotalPriceDiscount;

@Component
public class TotalPriceDiscountMapper implements GenericMapper<TotalPriceDiscountDTO, TotalPriceDiscount>
{
    @Override
    public TotalPriceDiscount mapFromDTO(TotalPriceDiscountDTO discountDTO)
    {
        return new TotalPriceDiscount(discountDTO.getMoneyLimitActivator()
            , discountDTO.getMoneyToReturn());
    }
    
    @Override
    public TotalPriceDiscountDTO mapToDTO(TotalPriceDiscount discount)
    {
        return new TotalPriceDiscountDTO(discount.getMoneyLimitActivator(), discount.getMoneyToReturn());
    }
}
