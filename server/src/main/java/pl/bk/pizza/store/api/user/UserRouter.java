package pl.bk.pizza.store.api.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.PATCH;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class UserRouter
{
    @Bean
    public RouterFunction<ServerResponse> routeUsers(UserHandler userHandler)
    {
        return route(POST("/users"), userHandler::addUser)
            .andRoute(POST("/auth"), userHandler::generateToken)
            .andRoute(GET("/users/{email:.+}"), userHandler::getUser)
            .andRoute(GET("/users/{email}/bonus"), userHandler::getBonusPoints)
            .andRoute(PATCH("/users/{email}/deactivate"), userHandler::deactivate);
    }
}
