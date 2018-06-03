package pl.bk.pizza.store.api;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import pl.bk.pizza.store.application.dto.order.NewOrderDTO;
import pl.bk.pizza.store.application.service.OrderService;
import pl.bk.pizza.store.domain.exception.AppException;
import pl.bk.pizza.store.domain.exception.ErrorCode;
import reactor.core.publisher.Mono;

import static java.net.URI.create;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@AllArgsConstructor
@Component
public class OrderHandler
{
    private final OrderService orderService;
    
    private static Mono<ServerResponse> handleException(Throwable exception)
    {
        if(exception instanceof AppException)
        {
            AppException e = (AppException) exception;
            return ServerResponse.status(e.getHttpStatus()).body(fromObject(e.getHttpStatus()));
        }
        return ServerResponse.badRequest().body(fromObject(new AppException("Internal error occure.", INTERNAL_SERVER_ERROR, ErrorCode.INTERNAL_ERROR)));
    }
    
    public Mono<ServerResponse> createOrder(ServerRequest request)
    {
        return Mono.from(request.bodyToMono(NewOrderDTO.class))
                   .flatMap(orderService::createOrder)
                   .flatMap(order -> ServerResponse.created(create("/orders/" + order.getId()))
                                                   .body(fromObject(order)))
                   .onErrorResume(OrderHandler::handleException);
    }
    
    public Mono<ServerResponse> getOrder(ServerRequest request)
    {
        final String orderId = request.pathVariable("orderId");
        return Mono.just(orderId)
                   .flatMap(orderService::getOrderById)
                   .flatMap(order -> ServerResponse.ok()
                                                   .body(fromObject(order)))
                   .onErrorResume(OrderHandler::handleException);
    }
    
    public Mono<ServerResponse> addProductToOrder(ServerRequest request)
    {
        final String orderId = request.pathVariable("orderId");
        final String productId = request.pathVariable("productId");
        
        return orderService
            .addProductToOrder(orderId, productId)
            .flatMap(order -> ServerResponse.ok().body(fromObject(order)))
            .onErrorResume(OrderHandler::handleException);
    }
    
    public Mono<ServerResponse> setToRealization(ServerRequest request)
    {
        final String orderId = request.pathVariable("orderId");
        return Mono.just(orderId)
                   .flatMap(orderService::setToRealization)
                   .flatMap(order -> ServerResponse.ok().body(fromObject(order)))
                   .onErrorResume(OrderHandler::handleException);
    }
    
    public Mono<ServerResponse> setToDelivered(ServerRequest request)
    {
        final String orderId = request.pathVariable("orderId");
        return Mono.just(orderId)
                   .flatMap(orderService::setToDelivered)
                   .flatMap(order -> ServerResponse.ok().body(fromObject(order)))
                   .onErrorResume(OrderHandler::handleException);
    }
}