package pl.bk.pizza.store.domain.order;

import java.time.Duration;
import java.util.List;

public interface OrderRepository {

    void save(Order order);

    Order getOrderById(String orderId);

    List<Order> findLastOrders(Duration time);
}
