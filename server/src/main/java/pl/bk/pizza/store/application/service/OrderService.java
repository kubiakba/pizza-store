package pl.bk.pizza.store.application.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.bk.common.dto.order.NewOrderDTO;
import pl.bk.common.dto.order.OrderDTO;
import pl.bk.common.dto.order.discount.DiscountDTO;
import pl.bk.pizza.store.application.mapper.order.DiscountMapper;
import pl.bk.pizza.store.application.mapper.order.NewOrderMapper;
import pl.bk.pizza.store.application.mapper.order.OrderMapper;
import pl.bk.pizza.store.domain.customer.user.User;
import pl.bk.pizza.store.domain.discount.DiscountProcessor;
import pl.bk.pizza.store.domain.order.Order;
import pl.bk.pizza.store.domain.order.OrderRepository;
import pl.bk.pizza.store.domain.product.BaseProductInfo;
import pl.bk.pizza.store.domain.product.ProductRepository;
import pl.bk.pizza.store.domain.service.PointsService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static pl.bk.pizza.store.domain.validator.order.OrderValidator.orderShouldExists;
import static pl.bk.pizza.store.domain.validator.product.ProductValidator.productShouldExists;

@Service
@AllArgsConstructor
@Slf4j
public class OrderService
{
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;
    private final NewOrderMapper newOrderMapper;
    private final UserService userService;
    private final PointsService pointsService;
    private final DiscountProcessor discountProcessor;
    private final DiscountMapper discountMapper;
    
    public Mono<OrderDTO> createOrder(NewOrderDTO orderDTO)
    {
        return Mono.just(newOrderMapper.mapFromDTO(orderDTO))
                   .flatMap(orderRepository::save)
                   .doOnNext(order -> log.info("Order with id:[{}] has been created.", order.getId()))
                   .map(orderMapper::mapToDTO);
    }
    
    public Mono<OrderDTO> addProductToOrder(String orderId, String productId)
    {
        final Mono<Order> order = orderRepository.findById(orderId)
                                                 .switchIfEmpty(orderShouldExists(orderId));
        
        final Mono<BaseProductInfo> product = productRepository.findById(productId)
                                                               .switchIfEmpty(productShouldExists(productId));
        
        return order
            .zipWith(product)
            .map(objects -> objects.getT1().addProduct(objects.getT2()))
            .flatMap(orderRepository::save)
            .map(orderMapper::mapToDTO);
    }
    
    public Mono<OrderDTO> addProductsToOrder(String orderId, String[] productIds)
    {
        return orderRepository.findById(orderId)
                              .switchIfEmpty(orderShouldExists(orderId))
                              .flatMap(order -> Flux
                                           .fromArray(productIds)
                                           .flatMap(productId -> productRepository.findById(productId)
                                                                                  .switchIfEmpty(productShouldExists(productId))
                                                   )
                                           .doOnNext(order::addProduct)
                                           .then(Mono.just(order))
                                      )
                              .flatMap(orderRepository::save)
                              .map(orderMapper::mapToDTO);
    }
    
    public Mono<OrderDTO> setToRealization(String orderId)
    {
        return orderRepository
            .findById(orderId)
            .map(Order::setToRealization)
            .switchIfEmpty(orderShouldExists(orderId))
            .flatMap(orderRepository::save)
            .doOnNext(order -> log.info("Order with id:[{}] has been set to realization.", order.getId()))
            .map(orderMapper::mapToDTO);
    }
    
    public Mono<OrderDTO> getOrderById(String orderId)
    {
        return orderRepository
            .findById(orderId)
            .switchIfEmpty(orderShouldExists(orderId))
            .map(orderMapper::mapToDTO);
    }
    
    public Mono<OrderDTO> setToDelivered(String orderId)
    {
        final Mono<Order> order = orderRepository.findById(orderId)
                                                 .map(Order::setToDelivered)
                                                 .switchIfEmpty(orderShouldExists(orderId));
        
        return order
            .flatMap(this::applyPointsToUser)
            .then(order)
            .flatMap(orderRepository::save)
            .doOnNext(ord -> log.info("Order with id:[{}] has been delivered.", ord.getId()))
            .map(orderMapper::mapToDTO);
    }
    
    public Mono<OrderDTO> applyDiscounts(String orderId)
    {
        final Mono<Order> order = orderRepository.findById(orderId)
                                                 .switchIfEmpty(orderShouldExists(orderId));
        
        return order
            .map(discountProcessor::applyDiscounts)
            .map(orderMapper::mapToDTO);
    }
    
    public Mono<OrderDTO> addDiscountToOrder(String orderId, DiscountDTO discountDTO)
    {
        return orderRepository.findById(orderId)
                              .switchIfEmpty(orderShouldExists(orderId))
                              .map(o -> o.addDiscount(discountMapper.mapFromDTO(discountDTO)))
                              .flatMap(orderRepository::save)
                              .map(orderMapper::mapToDTO);
    }
    
    private Mono<User> applyPointsToUser(Order order)
    {
        return userService.addPoints(order.getUserEmail(), pointsService.calculateAmountOfPoints(order));
    }
}
