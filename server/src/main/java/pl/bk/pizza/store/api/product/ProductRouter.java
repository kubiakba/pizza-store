package pl.bk.pizza.store.api.product;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ProductRouter
{
    @Bean
    public RouterFunction<ServerResponse> routeProducts(ProductHandler productHandler)
    {
        return route(POST("/products"), productHandler::createProduct)
            .andRoute(GET("/products"), productHandler::getAllProducts)
            .andRoute(GET("/products/available"), productHandler::getAllAvailableProducts)
            .andRoute(PUT("/products/{productId}/non-available"), productHandler::makeProductNonAvailable)
            .andRoute(GET("/products/pizzas"), productHandler::getAllPizzas)
            .andRoute(GET("/products/pizzaToppings"), productHandler::getAllPizzaToppings)
            .andRoute(GET("/products/kebabs"), productHandler::getAllKebabs)
            .andRoute(GET("/products/{productId}"), productHandler::getProduct)
            .andRoute(PUT("/products/{productId}/changePrice"), productHandler::changeProductPrice)
            .andRoute(GET("/products"), productHandler::getAllProducts);
    }
}
