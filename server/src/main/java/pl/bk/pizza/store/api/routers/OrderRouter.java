package pl.bk.pizza.store.api.routers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import pl.bk.pizza.store.api.OrderHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class OrderRouter
{
    @Bean
    public RouterFunction<ServerResponse> routes(OrderHandler orderHandler)
    {
        return route(POST("/orders"), orderHandler::createOrder)
            .andRoute(GET("/orders/{orderId}"), orderHandler::getOrder)
            .andRoute(PUT("/orders/{orderId}/to-realization"), orderHandler::setToRealization)
            .andRoute(PUT("/orders/{orderId}/delivered"), orderHandler::setToDelivered)
            .andRoute(PUT("/orders/{orderId}/{productId}"), orderHandler::addProductToOrder);
    }
}
