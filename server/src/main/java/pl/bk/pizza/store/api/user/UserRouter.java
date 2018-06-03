package pl.bk.pizza.store.api.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class UserRouter
{
    @Bean
    public RouterFunction<ServerResponse> routeUsers(UserHandler userHandler)
    {
        return route(POST("/users"), userHandler::addUser);
    }
}
