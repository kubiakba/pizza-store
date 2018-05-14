package pl.bk.pizza.store.domain.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;

/*@ControllerAdvice
@Slf4j*/
public class RestExceptionHandler /*extends ResponseEntityExceptionHandler*/
{
    /*@ExceptionHandler(value = AppException.class)
    public ResponseEntity<ErrorMessage> handleRequest(AppException exception, WebRequest request)
    {
        log.debug(exception.getMessage());
        
        return ResponseEntity
            .status(exception.getHttpStatus())
            .body(new ErrorMessage(exception.errorCode()));
    }
    */
    //TODO ADD GENERIC HANDLER -> Refactor to webflux
}
