package pl.bk.pizza.store.api.order;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import pl.bk.pizza.store.api.ErrorHandler;
import pl.bk.pizza.store.application.dto.order.NewOrderDTO;
import pl.bk.pizza.store.application.service.OrderService;
import pl.bk.pizza.store.domain.report.OrderReport;
import reactor.core.publisher.Mono;

import java.time.Duration;

import static java.net.URI.create;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@AllArgsConstructor
@Component
public class OrderHandler
{
    private final OrderService orderService;
    private final OrderReport report;
    
    Mono<ServerResponse> createOrder(ServerRequest request)
    {
        return Mono.from(request.bodyToMono(NewOrderDTO.class))
                   .flatMap(orderService::createOrder)
                   .flatMap(order -> ServerResponse.created(create("/orders/" + order.getId()))
                                                   .body(fromObject(order)))
                   .onErrorResume(ErrorHandler::handleException);
    }
    
    Mono<ServerResponse> getOrder(ServerRequest request)
    {
        final String orderId = request.pathVariable("orderId");
        
        return Mono.just(orderId)
                   .flatMap(orderService::getOrderById)
                   .flatMap(order -> ServerResponse.ok()
                                                   .body(fromObject(order)))
                   .onErrorResume(ErrorHandler::handleException);
    }
    
    Mono<ServerResponse> addProductToOrder(ServerRequest request)
    {
        final String orderId = request.pathVariable("orderId");
        final String productId = request.pathVariable("productId");
        
        return orderService
            .addProductToOrder(orderId, productId)
            .flatMap(order -> ServerResponse.ok().body(fromObject(order)))
            .onErrorResume(ErrorHandler::handleException);
    }
    
    Mono<ServerResponse> setToRealization(ServerRequest request)
    {
        final String orderId = request.pathVariable("orderId");
        
        return Mono.just(orderId)
                   .flatMap(orderService::setToRealization)
                   .flatMap(order -> ServerResponse.ok().body(fromObject(order)))
                   .onErrorResume(ErrorHandler::handleException);
    }
    
    Mono<ServerResponse> addEmailToOrder(ServerRequest request)
    {
        final String orderId = request.pathVariable("orderId");
        final String email = request.pathVariable("email");
        
        return orderService.addEmailToOrder(orderId, email)
                           .flatMap(order -> ServerResponse.ok().body(fromObject(order)))
                           .onErrorResume(ErrorHandler::handleException);
    }
    
    Mono<ServerResponse> setToDelivered(ServerRequest request)
    {
        final String orderId = request.pathVariable("orderId");
        
        return Mono.just(orderId)
                   .flatMap(orderService::setToDelivered)
                   .flatMap(order -> ServerResponse.ok().body(fromObject(order)))
                   .onErrorResume(ErrorHandler::handleException);
    }
    
    Mono<ServerResponse> generateLastOrdersReport(ServerRequest request)
    {
        final String timeInMinutes = request.pathVariable("timeInMinutes");
        final Duration duration = Duration.ofMinutes(Integer.parseInt(timeInMinutes));
        
        return report.generateLastOrdersReport(duration)
                     .flatMap(it -> ServerResponse.accepted().body(fromObject(Boolean.TRUE)))
                     .onErrorResume(ErrorHandler::handleException);
    }
}