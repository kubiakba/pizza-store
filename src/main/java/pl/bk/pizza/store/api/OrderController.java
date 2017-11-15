package pl.bk.pizza.store.api;

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
import pl.bk.pizza.store.application.service.OrderService;

import java.net.URI;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody NewOrderDTO orderDTO){
        OrderDTO order = orderService.createOrder(orderDTO);
        URI uri = URI.create("/orders/"+order.getId());
        return ResponseEntity.created(uri).body(order);
    }

    @ResponseStatus(OK)
    @GetMapping("/{orderId}")
    public OrderDTO getOrder(@PathVariable String orderId){
        return orderService.getOrderById(orderId);
    }

    @ResponseStatus(NO_CONTENT)
    @PutMapping("/{orderId}/{productId}")
    public void addProductToOrder(@PathVariable String orderId, @PathVariable String productId){
        orderService.addProductToOrder(orderId, productId);
    }

    @ResponseStatus(NO_CONTENT)
    @PutMapping("/{orderId}/to-realization")
    public void setStatusToRealization(@PathVariable String orderId) {
        orderService.setStatusToRealization(orderId);
    }
}
