package pl.bk.pizza.store.domain.exception;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import pl.bk.pizza.store.infrastructure.dao.error.ErrorMessage;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<ErrorMessage> handleRequest(AppException exception, WebRequest request){
        log.debug(exception.getMessage());

        return ResponseEntity
            .status(exception.getHttpStatus())
            .body(new ErrorMessage(exception.errorCode()));
    }
}
