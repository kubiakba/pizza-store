package pl.bk.pizza.store.application.mapper.order;

import org.springframework.stereotype.Component;
import pl.bk.common.dto.order.discount.DiscountDTO;
import pl.bk.common.dto.order.discount.ExtraProductDiscountDTO;
import pl.bk.common.dto.order.discount.PercentDiscountDTO;
import pl.bk.common.dto.order.discount.TotalPriceDiscountDTO;
import pl.bk.pizza.store.application.mapper.GenericMapper;
import pl.bk.pizza.store.domain.discount.Discount;
import pl.bk.pizza.store.domain.discount.bonus.ExtraProductDiscount;
import pl.bk.pizza.store.domain.discount.bonus.PercentDiscount;
import pl.bk.pizza.store.domain.discount.bonus.TotalPriceDiscount;
import pl.bk.pizza.store.domain.exception.UnsupportedTypeException;

import static pl.bk.pizza.store.domain.exception.ErrorCode.UNPROCESSABLE_TYPE;

@Component
public class DiscountMapper implements GenericMapper<DiscountDTO, Discount>
{
    @Override
    public Discount mapFromDTO(DiscountDTO discountDTO)
    {
        if(discountDTO instanceof ExtraProductDiscountDTO)
        {
            ExtraProductDiscountDTO discount = (ExtraProductDiscountDTO) discountDTO;
            return new ExtraProductDiscount(discount.getNumberOfBoughtProducts(), discount.getProductId(), discount.getExtraProducts());
        }
        if(discountDTO instanceof TotalPriceDiscountDTO)
        {
            TotalPriceDiscountDTO discount = (TotalPriceDiscountDTO) discountDTO;
            return new TotalPriceDiscount(discount.getMinAmountOfPaidMoneyToActivateDiscount(), discount.getMoneyToReturn());
        }
        if(discountDTO instanceof PercentDiscountDTO)
        {
            PercentDiscountDTO discount = (PercentDiscountDTO) discountDTO;
            return new PercentDiscount(discount.getDiscountPercent(), discount.getProductId());
        }
        throw new UnsupportedTypeException("Passed argument is null or cannot be parsed: " + discountDTO, UNPROCESSABLE_TYPE);
    }
    
    @Override
    public DiscountDTO mapToDTO(Discount discount)
    {
        if(discount instanceof ExtraProductDiscount)
        {
            ExtraProductDiscount disc = (ExtraProductDiscount) discount;
            return new ExtraProductDiscountDTO(disc.getNumberOfBoughtProducts(), disc.getProductId(), disc.getExtraProducts());
        }
        if(discount instanceof TotalPriceDiscount)
        {
            TotalPriceDiscount disc = (TotalPriceDiscount) discount;
            return new TotalPriceDiscountDTO(disc.getMinAmountOfPaidMoneyToActivateDiscount(), disc.getMoneyToReturn());
        }
        if(discount instanceof PercentDiscount)
        {
            PercentDiscount disc = (PercentDiscount) discount;
            return new PercentDiscountDTO(disc.getDiscountPercent(), disc.getProductId());
        }
        throw new UnsupportedTypeException("Passed argument is null or cannot be parsed: " + discount, UNPROCESSABLE_TYPE);
    }
}
