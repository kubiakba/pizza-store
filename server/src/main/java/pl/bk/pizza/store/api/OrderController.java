package pl.bk.pizza.store.api;

import lombok.AllArgsConstructor;
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

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
class OrderController
{
    private final OrderService orderService;
    
    @PostMapping
    @ResponseStatus(CREATED)
    public Mono<OrderDTO> createOrder(@RequestBody NewOrderDTO orderDTO)
    {
        return orderService.createOrder(orderDTO);
    }
    
    @ResponseStatus(OK)
    @GetMapping("/{orderId}")
    public Mono<OrderDTO> getOrder(@PathVariable String orderId)
    {
        return orderService.getOrderById(orderId);
    }
    
    @ResponseStatus(NO_CONTENT)
    @PutMapping("/{orderId}/{productId}")
    public Mono<OrderDTO> addProductToOrder(@PathVariable String orderId, @PathVariable String productId)
    {
        return orderService.addProductToOrder(orderId, productId);
    }
    
    @ResponseStatus(NO_CONTENT)
    @PutMapping("/{orderId}/to-realization")
    public Mono<OrderDTO> setStatusToRealization(@PathVariable String orderId)
    {
        return orderService.setStatusToRealization(orderId);
    }
    
    @ResponseStatus(NO_CONTENT)
    @PutMapping("/{orderId}/delivered")
    public Mono<OrderDTO> setStatusToDelivered(@PathVariable String orderId)
    {
        return orderService.setStatusToDelivered(orderId);
    }
    
    @ResponseStatus(NO_CONTENT)
    @PostMapping("/discounts/{orderId}")
    public Mono<OrderDTO> applyDiscount(@PathVariable String orderId, @RequestBody DiscountDTO discountDTO)
    {
        return orderService.applyDiscount(discountDTO, orderId);
    }
}
