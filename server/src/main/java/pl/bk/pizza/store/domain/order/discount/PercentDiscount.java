package pl.bk.pizza.store.domain.order.discount;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import pl.bk.pizza.store.domain.exception.AppException;
import pl.bk.pizza.store.domain.exception.ErrorCode;

import java.math.BigDecimal;

import static pl.bk.pizza.store.domain.exception.Preconditions.check;

@Getter
public class PercentDiscount implements Discount
{
    private final Integer discountPercent;
    
    public PercentDiscount(Integer discountPercent)
    {
        check(discountPercent > 100 || discountPercent < 0, () -> new AppException(
            "Value cannot be bigger than 100 or less than 0. Value: " + discountPercent,
            HttpStatus.UNPROCESSABLE_ENTITY,
            ErrorCode.INVALID_DISCOUNT_PERCENT
        ));
        
        this.discountPercent = discountPercent;
    }
    
    @Override
    public BigDecimal calculateDiscount(BigDecimal price)
    {
        return price.divide(new BigDecimal(discountPercent));
    }
}
