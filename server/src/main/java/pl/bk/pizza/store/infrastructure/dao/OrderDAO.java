package pl.bk.pizza.store.infrastructure.dao;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import pl.bk.pizza.store.domain.order.Order;
import pl.bk.pizza.store.domain.order.OrderRepository;
import pl.bk.pizza.store.domain.service.NowProvider;

import java.time.Duration;
import java.util.List;

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

    @Override
    public List<Order> findLastOrders(Duration time) {
        final Query query = new Query();
        Long hours = time.toHours();
        final Criteria orderDateTimeCriteria = Criteria.where("orderDateTime")
            .gt(NowProvider.now().minusHours(hours));
        query.addCriteria(orderDateTimeCriteria);
        return mongoTemplate.find(query, Order.class);
    }
}
