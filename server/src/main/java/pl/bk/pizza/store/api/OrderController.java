package pl.bk.pizza.store.api;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.bk.pizza.store.application.dto.order.NewOrderDTO;
import pl.bk.pizza.store.application.dto.order.OrderDTO;
import pl.bk.pizza.store.application.dto.order.discount.DiscountDTO;
import pl.bk.pizza.store.application.service.OrderService;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
class OrderController
{
    private final OrderService orderService;
    
    @PostMapping
    public Mono<ResponseEntity<OrderDTO>> createOrder(@RequestBody NewOrderDTO orderDTO)
    {
        final Mono<OrderDTO> order = orderService.createOrder(orderDTO);
        
        final Mono<URI> uri = order
            .map(OrderDTO::getId)
            .map(id -> URI.create("/orders/" + id));
        
        return uri
            .zipWith(order)
            .map(objects -> ResponseEntity.created(objects.getT1()).body(objects.getT2()));
    }
    
    @ResponseStatus(OK)
    @GetMapping("/{orderId}")
    public Mono<OrderDTO> getOrder(@PathVariable String orderId)
    {
        return orderService.getOrderById(orderId);
    }
    
    @ResponseStatus(NO_CONTENT)
    @PutMapping("/{orderId}/{productId}")
    public void addProductToOrder(@PathVariable String orderId, @PathVariable String productId)
    {
        orderService.addProductToOrder(orderId, productId);
    }
    
    @ResponseStatus(NO_CONTENT)
    @PutMapping("/{orderId}/to-realization")
    public void setStatusToRealization(@PathVariable String orderId)
    {
        orderService.setStatusToRealization(orderId);
    }
    
    @ResponseStatus(NO_CONTENT)
    @PutMapping("/{orderId}/delivered")
    public void setStatusToDelivered(@PathVariable String orderId)
    {
        orderService.setStatusToDelivered(orderId);
    }
    
    @ResponseStatus(NO_CONTENT)
    @PostMapping("/discounts/{orderId}")
    public void applyDiscount(@PathVariable String orderId, @RequestBody DiscountDTO discountDTO)
    {
        orderService.applyDiscount(discountDTO, orderId);
    }
}
