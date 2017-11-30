package pl.bk.pizza.store.domain.order.discount.price;

import pl.bk.pizza.store.domain.order.discount.Discount;

import java.math.BigDecimal;

public class TotalPriceDiscount implements Discount {

    private final BigDecimal minAmountOfPaidMoneyToActivateDiscount;
    private final BigDecimal moneyToReturn;

    public TotalPriceDiscount(BigDecimal minAmountOfPaidMoneyToActivateDiscount,
                              BigDecimal moneyToReturn ) {
        this.minAmountOfPaidMoneyToActivateDiscount = minAmountOfPaidMoneyToActivateDiscount;
        this.moneyToReturn = moneyToReturn;
    }

    @Override
    public BigDecimal calculateDiscount(BigDecimal sumOfShopping) {
        if (minAmountOfPaidMoneyToActivateDiscount.compareTo(sumOfShopping) > 0) {
            return sumOfShopping.subtract(moneyToReturn);
        } else {
            return sumOfShopping;
        }
    }

    public BigDecimal getMinAmountOfPaidMoneyToActivateDiscount() {
        return minAmountOfPaidMoneyToActivateDiscount;
    }

    public BigDecimal getMoneyToReturn() {
        return moneyToReturn;
    }
}
