package pl.bk.pizza.store.domain.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import pl.bk.pizza.store.domain.order.Order;
import java.math.BigDecimal;

@Service
public class PointsService {

    @Value("${price-point-multiplier}")
    private String multiplier;

    public int applyPoints(Order order){
        return order.calculateTotalPrice().multiply(new BigDecimal(multiplier)).intValue();
    }
}
