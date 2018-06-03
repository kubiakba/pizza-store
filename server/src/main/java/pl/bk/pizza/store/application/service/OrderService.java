package pl.bk.pizza.store.application.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bk.pizza.store.application.dto.order.NewOrderDTO;
import pl.bk.pizza.store.application.dto.order.OrderDTO;
import pl.bk.pizza.store.application.mapper.order.NewOrderMapper;
import pl.bk.pizza.store.application.mapper.order.OrderMapper;
import pl.bk.pizza.store.domain.customer.user.User;
import pl.bk.pizza.store.domain.order.Order;
import pl.bk.pizza.store.domain.order.OrderRepository;
import pl.bk.pizza.store.domain.product.BaseProductInfo;
import pl.bk.pizza.store.domain.product.ProductRepository;
import pl.bk.pizza.store.domain.service.PointsService;
import reactor.core.publisher.Mono;

import static pl.bk.pizza.store.domain.validator.order.OrderValidator.orderShouldExists;
import static pl.bk.pizza.store.domain.validator.product.ProductValidator.productShouldExists;

@Service
@AllArgsConstructor
public class OrderService
{
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;
    private final NewOrderMapper newOrderMapper;
    private final UserService userService;
    private final PointsService pointsService;
    
    public Mono<OrderDTO> createOrder(NewOrderDTO orderDTO)
    {
        return Mono.just(newOrderMapper.mapFromDTO(orderDTO))
                   .flatMap(orderRepository::save)
                   .map(orderMapper::mapToDTO);
    }
    
    public Mono<OrderDTO> addProductToOrder(String orderId, String productId)
    {
        final Mono<Order> order = orderRepository.findById(orderId)
                                                 .doOnNext(it -> orderShouldExists(it, orderId));
        
        final Mono<BaseProductInfo> product = productRepository.findById(productId)
                                                               .doOnNext(it -> productShouldExists(it, productId));
        
        return order
            .zipWith(product)
            .doOnNext(objects -> objects.getT1().addProduct(objects.getT2()))
            .flatMap(objects -> orderRepository.save(objects.getT1()))
            .map(orderMapper::mapToDTO);
    }
    
    public Mono<OrderDTO> setToRealization(String orderId)
    {
        return orderRepository
            .findById(orderId)
            .doOnNext(it -> orderShouldExists(it, orderId))
            .doOnNext(Order::setToRealization)
            .flatMap(orderRepository::save)
            .map(orderMapper::mapToDTO);
    }
    
    public Mono<OrderDTO> getOrderById(String orderId)
    {
        return orderRepository
            .findById(orderId)
            .map(orderMapper::mapToDTO);
    }
    
    public Mono<OrderDTO> setToDelivered(String orderId)
    {
        final Mono<Order> order = orderRepository.findById(orderId)
                                                 .doOnNext(it -> orderShouldExists(it, orderId))
                                                 .doOnNext(Order::setToDelivered);
    
        final Mono<User> appliedPoints = order.flatMap(this::applyPointsToUser);
        
        return order.zipWith(appliedPoints)
            .flatMap(objects->orderRepository.save(objects.getT1()))
            .map(orderMapper::mapToDTO);
    }
    
    private Mono<User> applyPointsToUser(Order order)
    {
        return userService.addPoints(order.getUserEmail(), pointsService.calculateAmountOfPoints(order));
    }
}
