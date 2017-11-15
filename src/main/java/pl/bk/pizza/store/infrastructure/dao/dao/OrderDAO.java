package pl.bk.pizza.store.infrastructure.dao.dao;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import pl.bk.pizza.store.domain.order.Order;
import pl.bk.pizza.store.domain.order.OrderRepository;

@Repository
public class OrderDAO implements OrderRepository {

    private static final String  COLLECTION_NAME = "order";

    private final MongoTemplate mongoTemplate;

    public OrderDAO(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void save(Order order) {
        mongoTemplate.save(order, COLLECTION_NAME);
    }

    @Override
    public Order getOrderById(String orderId) {
        return mongoTemplate.findById(orderId, Order.class, COLLECTION_NAME);
    }
}
