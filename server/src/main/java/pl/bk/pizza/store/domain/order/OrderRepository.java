package pl.bk.pizza.store.domain.order;

public interface OrderRepository {

    void save(Order order);

    Order getOrderById(String orderId);
}
