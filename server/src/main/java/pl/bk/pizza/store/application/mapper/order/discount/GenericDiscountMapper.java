package pl.bk.pizza.store.application.mapper.order.discount;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.bk.pizza.store.application.dto.order.discount.DiscountDTO;
import pl.bk.pizza.store.application.dto.order.discount.PercentDiscountDTO;
import pl.bk.pizza.store.application.dto.order.discount.TotalPriceDiscountDTO;
import pl.bk.pizza.store.application.mapper.GenericMapper;
import pl.bk.pizza.store.domain.order.discount.Discount;
import pl.bk.pizza.store.domain.order.discount.PercentDiscount;
import pl.bk.pizza.store.domain.order.discount.TotalPriceDiscount;

@AllArgsConstructor
@Component
public class GenericDiscountMapper implements GenericMapper<DiscountDTO, Discount>
{
    @Override
    public Discount mapFromDTO(DiscountDTO discountDTO)
    {
        final Class clazz = discountDTO.getClass();
        
        if(clazz.equals(PercentDiscountDTO.class))
        {
            final PercentDiscountDTO percentDiscountDTO = (PercentDiscountDTO) discountDTO;
            return new PercentDiscount(percentDiscountDTO.getDiscountPercent());
        }
        
        if(clazz.equals(TotalPriceDiscount.class))
        {
            final TotalPriceDiscountDTO discount = (TotalPriceDiscountDTO) discountDTO;
            return new TotalPriceDiscount(discount.getMoneyLimitActivator(), discount.getMoneyLimitActivator());
        }
        
        throw new IllegalArgumentException("Passed argument is null or cannot be parsed: " + discountDTO);
    }
    
    @Override
    public DiscountDTO mapToDTO(Discount discount)
    {
        final Class clazz = discount.getClass();
        
        if(clazz.equals(PercentDiscount.class))
        {
            final PercentDiscount percentDiscount = (PercentDiscount) discount;
            return new PercentDiscountDTO(percentDiscount.getDiscountPercent());
        }
        
        if(clazz.equals(TotalPriceDiscount.class))
        {
            final TotalPriceDiscount totalPriceDiscount = (TotalPriceDiscount) discount;
            return new TotalPriceDiscountDTO(totalPriceDiscount.getMoneyLimitActivator(), totalPriceDiscount.getMoneyLimitActivator());
        }
        
        throw new IllegalArgumentException("Passed argument is null or cannot be parsed: " + discount);
    }
}
