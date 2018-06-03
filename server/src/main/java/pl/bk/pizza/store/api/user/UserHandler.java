package pl.bk.pizza.store.api.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import pl.bk.pizza.store.api.ErrorHandler;
import pl.bk.pizza.store.application.dto.user.NewUserDTO;
import pl.bk.pizza.store.application.service.UserService;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@Component
@AllArgsConstructor
public class UserHandler
{
    private final UserService userService;
    
    public Mono<ServerResponse> addUser(ServerRequest request)
    {
        return request
            .bodyToMono(NewUserDTO.class)
            .flatMap(userService::createUser)
            .flatMap(it -> ServerResponse.created(URI.create("/users/" + it.getEmail()))
                                         .body(fromObject(it)))
            .onErrorResume(ErrorHandler::handleException);
    }
}
