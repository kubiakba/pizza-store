package pl.bk.pizza.store.application.service;

import org.springframework.stereotype.Service;

import pl.bk.pizza.store.application.dto.order.NewOrderDTO;
import pl.bk.pizza.store.application.dto.order.OrderDTO;
import pl.bk.pizza.store.application.utils.DtoMapper;
import pl.bk.pizza.store.domain.exception.MissingEntityException;
import pl.bk.pizza.store.domain.order.Order;
import pl.bk.pizza.store.domain.order.OrderRepository;
import pl.bk.pizza.store.domain.product.ProductRepository;

import static pl.bk.pizza.store.domain.exception.ErrorCode.*;
import static pl.bk.pizza.store.domain.exception.Preconditions.*;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final DtoMapper dtoMapper;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository,
                        DtoMapper dtoMapper) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.dtoMapper = dtoMapper;
    }

    public OrderDTO createOrder(NewOrderDTO orderDTO) {
        Order order = dtoMapper.mapTo(orderDTO);
        orderRepository.save(order);
        return dtoMapper.mapTo(order);
    }

    public void addProductToOrder(String orderId, String productId) {
        final Order order = orderRepository.getOrderById(orderId);
        check(order == null,() -> new MissingEntityException("Cannot find order with id: " + orderId,
            MISSING_ORDER));
        order.addProduct(productRepository.getProductById(productId));
        orderRepository.save(order);
    }

    public void setStatusToRealization(String orderId){
        final Order order = orderRepository.getOrderById(orderId);
        check(order == null,() -> new MissingEntityException("Cannot find order with id: " + orderId,
            MISSING_ORDER));
        order.setToRealization();
        orderRepository.save(order);
    }

    public OrderDTO getOrderById(String orderId) {
        return dtoMapper.mapTo(orderRepository.getOrderById(orderId));
    }
}
