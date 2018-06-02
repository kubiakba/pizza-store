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

import java.util.function.Function;

import static java.net.URI.create;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@AllArgsConstructor
@Component
public class OrderRouter
{
    private final OrderService orderService;
    
    public Mono<ServerResponse> createOrder(ServerRequest request)
    {
        return Mono.from(request.bodyToMono(NewOrderDTO.class))
                   .flatMap(orderService::createOrder)
                   .map(order -> ServerResponse.created(create("/orders/" + order.getId()))
                                               .body(fromObject(order)))
                   .onErrorResume(OrderRouter::handleException)
                   .flatMap(Function.identity());
    }
    
    public Mono<ServerResponse> getOrder(ServerRequest request)
    {
        final String orderId = request.pathVariable("id");
        return Mono.just(orderId)
                   .flatMap(orderService::getOrderById)
                   .map(order -> ServerResponse.ok()
                                               .body(fromObject(order)))
                   .onErrorResume(OrderRouter::handleException)
                   .flatMap(Function.identity());
    }
    
    private static Mono<Mono<ServerResponse>> handleException(Throwable exception)
    {
        if(exception instanceof AppException)
        {
            AppException e = (AppException) exception;
            return Mono.just(ServerResponse.status(e.getHttpStatus()).body(fromObject(e.getHttpStatus())));
        }
        return Mono.just(ServerResponse.badRequest().body(fromObject(new AppException("Internal error occure.", INTERNAL_SERVER_ERROR, ErrorCode.INTERNAL_ERROR))));
    }
}