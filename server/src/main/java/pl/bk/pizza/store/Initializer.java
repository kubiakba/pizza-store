package pl.bk.pizza.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import pl.bk.pizza.store.api.OrderRouter;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@SpringBootApplication
public class Initializer
{
    public static void main(String[] args)
    {
        SpringApplication.run(Initializer.class, args);
    }
    
    @Bean
    public RouterFunction<ServerResponse> routes(OrderRouter orderRouter)
    {
        return route(POST("/orders"), orderRouter::createOrder)
            .andRoute(GET("/orders/{orderId}"),orderRouter::getOrder)
            .andRoute(PUT("/orders/{orderId}/to-realization"),orderRouter::setToRealization)
            .andRoute(PUT("/orders/{orderId}/delivered"),orderRouter::setToDelivered)
            .andRoute(PUT("/orders/{orderId}/{productId}"),orderRouter::addProductToOrder);
    }
}

