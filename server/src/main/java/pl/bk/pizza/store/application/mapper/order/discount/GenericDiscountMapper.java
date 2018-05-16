package pl.bk.pizza.store.application.mapper.order.discount;

import org.springframework.stereotype.Component;
import pl.bk.pizza.store.application.dto.order.discount.DiscountDTO;
import pl.bk.pizza.store.application.dto.order.discount.PercentDiscountDTO;
import pl.bk.pizza.store.application.dto.order.discount.TotalPriceDiscountDTO;
import pl.bk.pizza.store.application.mapper.GenericMapper;
import pl.bk.pizza.store.domain.order.discount.Discount;
import pl.bk.pizza.store.domain.order.discount.PercentDiscount;
import pl.bk.pizza.store.domain.order.discount.TotalPriceDiscount;

@Component
public class GenericDiscountMapper implements GenericMapper<DiscountDTO, Discount>
{
    @Override
    public Discount mapFromDTO(DiscountDTO discountDTO)
    {
        if(discountDTO instanceof PercentDiscountDTO)
        {
            final PercentDiscountDTO percentDiscountDTO = (PercentDiscountDTO) discountDTO;
            return new PercentDiscount(percentDiscountDTO.getDiscountPercent());
        }
        
        if(discountDTO instanceof TotalPriceDiscount)
        {
            final TotalPriceDiscountDTO discount = (TotalPriceDiscountDTO) discountDTO;
            return new TotalPriceDiscount(discount.getMoneyLimitActivator(), discount.getMoneyLimitActivator());
        }
        
        throw new IllegalArgumentException("Passed argument is null or cannot be parsed: " + discountDTO);
    }
    
    @Override
    public DiscountDTO mapToDTO(Discount discount)
    {
        if(discount instanceof PercentDiscount)
        {
            final PercentDiscount percentDiscount = (PercentDiscount) discount;
            return new PercentDiscountDTO(percentDiscount.getDiscountPercent());
        }
        
        if(discount instanceof TotalPriceDiscount)
        {
            final TotalPriceDiscount totalPriceDiscount = (TotalPriceDiscount) discount;
            return new TotalPriceDiscountDTO(totalPriceDiscount.getMoneyLimitActivator(), totalPriceDiscount.getMoneyLimitActivator());
        }
        
        throw new IllegalArgumentException("Passed argument is null or cannot be parsed: " + discount);
    }
}
