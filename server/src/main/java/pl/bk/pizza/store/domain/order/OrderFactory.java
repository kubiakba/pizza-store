package pl.bk.pizza.store.domain.order;

import org.springframework.stereotype.Service;

import pl.bk.pizza.store.domain.service.IdGenerator;

@Service
public class OrderFactory {

    private final IdGenerator idGenerator;

    public OrderFactory(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    public Order createOrder(String email) {
        return new Order(idGenerator.generateID(), email);
    }
}
