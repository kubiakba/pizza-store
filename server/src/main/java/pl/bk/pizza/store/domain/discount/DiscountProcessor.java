package pl.bk.pizza.store.domain.discount;

import lombok.AllArgsConstructor;
import pl.bk.pizza.store.domain.discount.rule.DiscountRule;
import pl.bk.pizza.store.domain.discount.rule.DiscountRuleProcessor;
import pl.bk.pizza.store.domain.order.Order;

@AllArgsConstructor
public class DiscountProcessor
{
    private final DiscountRule rule;
    private final DiscountRuleProcessor ruleProcessor;
    
    void applyDiscounts(Order order)
    {
        ruleProcessor.process(rule, order)
                     .forEach((key, value) -> order.getDiscounts()
                                                   .stream()
                                                   .filter(discount -> discount.getClass().equals(value))
                                                   .forEach(discount -> discount.apply(order)));
    }
}
