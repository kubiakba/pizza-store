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
import pl.bk.pizza.store.domain.product.BaseProductInfo;
import pl.bk.pizza.store.domain.product.ProductRepository;
import pl.bk.pizza.store.domain.service.PointsService;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import static pl.bk.pizza.store.domain.exception.ErrorCode.MISSING_ORDER;
import static pl.bk.pizza.store.domain.exception.ErrorCode.MISSING_PRODUCT;
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
    
    public Mono<OrderDTO> applyDiscount(DiscountDTO discountDTO, String orderId)
    {
        final Mono<Order> order = orderRepository.findById(orderId)
                                                 .doOnNext(it -> orderShouldExists(it, orderId));
        
        final Mono<Discount> discount = Mono.just(discountMapper.mapFromDTO(discountDTO));
        
        return order
            .zipWith(discount)
            .doOnNext(objects -> objects.getT1().addDiscount(objects.getT2()))
            .flatMap(objects -> orderRepository.save(objects.getT1()))
            .map(orderMapper::mapToDTO);
    }
    
    public Mono<OrderDTO> setToDelivered(String orderId)
    {
        return orderRepository
            .findById(orderId)
            .doOnNext(it -> orderShouldExists(it, orderId))
            .doOnNext(Order::setToDelivered)
            .doOnNext(this::applyPointsToUser)
            .flatMap(orderRepository::save)
            .map(orderMapper::mapToDTO);
    }
    
    private Mono<Order> applyPointsToUser(Order order)
    {
        final Mono<Order> orderMono = Mono.just(order);
        final Mono<Integer> points = orderMono.map(pointsService::applyPoints);
        
        return points
            .zipWith(orderMono)
            .doOnNext(objects -> userService.addPointsToUser(objects.getT2().getUserEmail(), objects.getT1()))
            .map(Tuple2::getT2);
    }
    
    // TODO Move these methods to Validators classes
    
    private void orderShouldExists(Order order, String id)
    {
        check(order == null, () -> new MissingEntityException(
            "Cannot find order with id: " + id,
            MISSING_ORDER
        ));
    }
    
    private void productShouldExists(BaseProductInfo product, String productId)
    {
        check(product == null, () -> new MissingEntityException(
            "Product with id: " + productId + "does not exists",
            MISSING_PRODUCT
        ));
    }
}
