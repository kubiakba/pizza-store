package pl.bk.pizza.store.application.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.bk.pizza.store.application.dto.order.NewOrderDTO;
import pl.bk.pizza.store.application.dto.order.OrderDTO;
import pl.bk.pizza.store.application.dto.order.discount.DiscountDTO;
import pl.bk.pizza.store.application.mapper.order.NewOrderMapper;
import pl.bk.pizza.store.application.mapper.order.OrderMapper;
import pl.bk.pizza.store.application.mapper.order.discount.GenericDiscountMapper;
import pl.bk.pizza.store.domain.exception.MissingEntityException;
import pl.bk.pizza.store.domain.order.Order;
import pl.bk.pizza.store.domain.order.OrderRepository;
import pl.bk.pizza.store.domain.order.discount.Discount;
import pl.bk.pizza.store.domain.product.Product;
import pl.bk.pizza.store.domain.product.ProductRepository;
import pl.bk.pizza.store.domain.service.PointsService;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import static pl.bk.pizza.store.domain.exception.ErrorCode.MISSING_ORDER;
import static pl.bk.pizza.store.domain.exception.Preconditions.check;

@Service
@AllArgsConstructor
public class OrderService
{
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;
    private final NewOrderMapper newOrderMapper;
    private final GenericDiscountMapper discountMapper;
    private final UserService userService;
    private final PointsService pointsService;
    
    public Mono<OrderDTO> createOrder(NewOrderDTO orderDTO)
    {
        return Mono.just(newOrderMapper.mapFromDTO(orderDTO))
                   .doOnNext(orderRepository::save)
                   .map(orderMapper::mapToDTO);
    }
    
    public void addProductToOrder(String orderId, String productId)
    {
        final Mono<Order> order = orderRepository.findById(orderId)
                                                 .doOnNext(it -> check(it == null, () -> new MissingEntityException(
                                                     "Cannot find order with id: " + orderId, MISSING_ORDER
                                                 )));
        
        final Mono<Product> product = productRepository.findById(productId);
        
        order.zipWith(product)
             .doOnNext(objects -> objects.getT1().addProduct(objects.getT2()))
             .doOnNext(objects -> orderRepository.save(objects.getT1()));
    }
    
    public void setStatusToRealization(String orderId)
    {
        final Mono<Order> order = orderRepository.findById(orderId);
        
        order.doOnNext((x) -> check(order == null, () -> new MissingEntityException(
            "Cannot find order with id: " + orderId,
            MISSING_ORDER
        )));
        
        order.doOnNext(Order::setToRealization);
        order.doOnNext(orderRepository::save);
    }
    
    public Mono<OrderDTO> getOrderById(String orderId)
    {
        return orderRepository.findById(orderId).map(orderMapper::mapToDTO);
    }
    
    public void applyDiscount(DiscountDTO discountDTO, String orderId)
    {
        final Mono<Order> order = orderRepository.findById(orderId);
        order.doOnNext((x) -> check(order == null, () -> new MissingEntityException(
            "Cannot find order with id: " + orderId,
            MISSING_ORDER
        )));
        
        final Mono<Discount> discount = Mono.just(discountMapper.mapFromDTO(discountDTO));
        
        final Mono<Tuple2<Order, Discount>> orderDiscountMono = order.zipWith(discount);
        orderDiscountMono.doOnNext(objects -> objects.getT1().addDiscount(objects.getT2()));
        orderDiscountMono.doOnNext(objects -> orderRepository.save(objects.getT1()));
    }
    
    public void setStatusToDelivered(String orderId)
    {
        orderRepository.findById(orderId)
                       .doOnNext((order) -> check(order == null, () -> new MissingEntityException(
                           "Cannot find order with id: " + orderId,
                           MISSING_ORDER
                       )))
                       .doOnNext(Order::setToDelivered)
                       .doOnNext(this::applyPointsToUser)
                       .doOnNext(orderRepository::save);
    }
    
    private Mono<Order> applyPointsToUser(Order order)
    {
        final Mono<Order> orderMono = Mono.just(order);
        final Mono<Integer> points = orderMono.map(pointsService::applyPoints);
        
        return points
            .zipWith(orderMono)
            .doOnNext(objects -> userService.addPointsToUser(objects.getT2().getUserEmail(), objects.getT1()))
            .map(objects -> objects.getT2());
    }
}
