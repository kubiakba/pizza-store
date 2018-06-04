package pl.bk.pizza.store.api.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import pl.bk.pizza.store.api.ErrorHandler;
import pl.bk.pizza.store.application.dto.user.NewUserDTO;
import pl.bk.pizza.store.application.dto.user.UserDTO;
import pl.bk.pizza.store.application.service.UserService;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@Component
@AllArgsConstructor
public class UserHandler
{
    private final UserService userService;
    
    Mono<ServerResponse> addUser(ServerRequest request)
    {
        return request
            .bodyToMono(NewUserDTO.class)
            .flatMap(userService::createUser)
            .flatMap(it -> ServerResponse.created(URI.create("/users/" + it.getEmail()))
                                         .body(fromObject(it)))
            .onErrorResume(ErrorHandler::handleException);
    }
    
    Mono<ServerResponse> getUser(ServerRequest request)
    {
        final String email = request.pathVariable("email");
        
        return userService.getUser(email)
                          .flatMap(it -> ServerResponse.ok().body(fromObject(it)))
                          .onErrorResume(ErrorHandler::handleException);
    }
    
    Mono<ServerResponse> getBonusPoints(ServerRequest request)
    {
        final String email = request.pathVariable("email");
        
        return userService.getBonusPoints(email)
                          .flatMap(it -> ServerResponse.ok().body(fromObject(it)))
                          .onErrorResume(ErrorHandler::handleException);
    }
    
    Mono<ServerResponse> deactivate(ServerRequest request)
    {
        final String email = request.pathVariable("email");
        
        return userService.deactivateUser(email)
                          .flatMap(it -> ServerResponse.ok().body(fromObject(it)))
                          .onErrorResume(ErrorHandler::handleException);
    }
    
}
