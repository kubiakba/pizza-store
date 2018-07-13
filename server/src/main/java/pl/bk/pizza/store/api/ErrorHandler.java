package pl.bk.pizza.store.api;

import lombok.NoArgsConstructor;
import org.springframework.web.reactive.function.server.ServerResponse;
import pl.bk.pizza.store.domain.exception.AppException;
import pl.bk.pizza.store.infrastructure.error.ErrorMessage;
import reactor.core.publisher.Mono;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static pl.bk.pizza.store.domain.exception.ErrorCode.INTERNAL_ERROR;

@NoArgsConstructor(access = PRIVATE)
public class ErrorHandler
{
    public static Mono<ServerResponse> handleException(Throwable exception)
    {
        if(exception instanceof AppException)
        {
            AppException e = (AppException) exception;
            return ServerResponse.status(e.getHttpStatus()).body(fromObject(new ErrorMessage(e.errorCode())));
        }
        return ServerResponse.status(INTERNAL_SERVER_ERROR).body(fromObject(new ErrorMessage(INTERNAL_ERROR)));
    }
}
